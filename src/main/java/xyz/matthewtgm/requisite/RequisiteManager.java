/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.matthewtgm.requisite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.framing.CloseFrame;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;
import xyz.matthewtgm.json.util.JsonHelper;
import xyz.matthewtgm.requisite.Requisite;
import xyz.matthewtgm.requisite.hud.HudManager;
import xyz.matthewtgm.requisite.hypixel.HypixelManager;
import xyz.matthewtgm.requisite.keybinds.KeyBindManager;
import xyz.matthewtgm.requisite.players.PlayerDataManager;
import xyz.matthewtgm.requisite.players.cosmetics.CosmeticManager;
import xyz.matthewtgm.requisite.files.ConfigHandler;
import xyz.matthewtgm.requisite.files.DataHandler;
import xyz.matthewtgm.requisite.files.FileHandler;
import xyz.matthewtgm.requisite.keybinds.KeyBindConfigHandler;
import xyz.matthewtgm.requisite.players.indicators.IndicatorManager;
import xyz.matthewtgm.requisite.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.networking.packets.impl.other.GameClosePacket;
import xyz.matthewtgm.requisite.networking.packets.impl.other.GameOpenPacket;
import xyz.matthewtgm.requisite.util.*;
import xyz.matthewtgm.requisite.util.global.GlobalMinecraft;
import xyz.matthewtgm.tgmconfig.Configuration;

import java.net.URI;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class RequisiteManager {

    private static boolean webSocketTest = false;
    private static boolean webSocketDebug = false;

    private static final Logger logger = LogManager.getLogger(Requisite.NAME + " (Manager)");

    /* Requisite managers. */
    private FileHandler fileHandler;
    private ConfigHandler configHandler;
    private KeyBindConfigHandler keyBindConfigHandler;
    private DataHandler dataHandler;
    private RequisiteClientSocket webSocket;
    private PlayerDataManager dataManager;
    private CosmeticManager cosmeticManager;
    private IndicatorManager indicatorManager;

    /* Managers for other mods. */
    private KeyBindManager keyBindManager;
    private HudManager hudManager;
    private HypixelManager hypixelManager;

    public void initialize() {
        checkJvmProperties();
    }

    private void checkJvmProperties() {
        try {
            if (!webSocketTest)
                webSocketTest = Boolean.parseBoolean(System.getProperty("requisiteWebSocketTest", "false"));
            if (!webSocketDebug)
                webSocketDebug = Boolean.parseBoolean(System.getProperty("requisiteWebSocketDebug", "false"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            /* Requisite. */
            (fileHandler = new FileHandler()).start();
            (configHandler = new ConfigHandler("config", fileHandler.getRequisiteDir())).start();
            (keyBindConfigHandler = new KeyBindConfigHandler("keybinds", fileHandler.getRequisiteDir())).update();
            (dataHandler = new DataHandler("data", fileHandler.getRequisiteDir())).start();
            fixSocket();
            if (!webSocket.isOpen())
                scheduleSocketReconnect();
            if (dataHandler.isMayLogData())
                webSocket.send(new GameOpenPacket(GlobalMinecraft.getSession().getProfile().getId().toString()));
            (dataManager = new PlayerDataManager()).start();
            (cosmeticManager = new CosmeticManager()).start();
            (indicatorManager = new IndicatorManager()).start();

            /* Mods. */
            keyBindManager = new KeyBindManager();
            hudManager = new HudManager(new Configuration("hud", fileHandler.getRequisiteDir()));
            hypixelManager = new HypixelManager();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (dataHandler.isMayLogData())
                    webSocket.send(new GameClosePacket(GlobalMinecraft.getSession().getProfile().getId().toString()));
                webSocket.close(CloseFrame.NORMAL, "Game shutdown");
            }, Requisite.NAME + " Shutdown"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URI websocketUri() {
        if (webSocketTest)
            return URI.create("ws://localhost:8080");
        JsonObject object = JsonApiHelper.getJsonObject("https://raw.githubusercontent.com/TGMDevelopment/RequisiteData/main/websocket.json", true);
        String uri = object.get("uri").getAsString();
        if (webSocketDebug) {
            logger.info(JsonHelper.makePretty(object));
            logger.info("Pre: " + uri);
        }
        for (int i = 0; i < object.get("loop").getAsInt(); i++)
            uri = new String(Base64.getDecoder().decode(uri));
        if (webSocketDebug) {
            logger.info("Post: " + uri);
        }
        return URI.create(uri);
    }

    private RequisiteClientSocket createWebSocket(RequisiteClientSocket original) {
        original.setConnectionLostTimeout(120);
        original.setTcpNoDelay(true);
        return original;
    }

    public void fixSocket() {
        try {
            if (!createSocket()) {
                Notifications.push("Failed to connect to " + Requisite.NAME + " WebSocket!", "Click me to attempt a reconnect.", notification -> {
                    try {
                        String originalTitle = notification.title;
                        String originalDescription = notification.description;
                        notification.title = "Reconnecting...";
                        notification.description = "";
                        if (!webSocket.reconnectBlocking()) {
                            notification.title = "Failed to reconnect! D:";

                            Notifications.Notification resubmitted = notification.clone();
                            resubmitted.title = originalTitle;
                            resubmitted.description = originalDescription;
                            Notifications.push(resubmitted);
                        } else
                            notification.title = "Reconnected successfully!";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean createSocket() throws Exception {
        return (webSocket = createWebSocket(new RequisiteClientSocket(websocketUri()))).connectBlocking(15, TimeUnit.SECONDS);
    }

    public void scheduleSocketReconnect() {
        Multithreading.schedule(new Thread(() -> {
            try {
                if (!webSocket.reconnectBlocking())
                    scheduleSocketReconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Requisite WebSocket Reconnect Thread"), 15, TimeUnit.MINUTES);
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    public KeyBindConfigHandler getKeyBindConfigHandler() {
        return keyBindConfigHandler;
    }

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    public PlayerDataManager getDataManager() {
        return dataManager;
    }

    public RequisiteClientSocket getWebSocket() {
        return webSocket;
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }

    public IndicatorManager getIndicatorManager() {
        return indicatorManager;
    }

    public KeyBindManager getKeyBindManager() {
        return keyBindManager;
    }

    public HudManager getHudManager() {
        return hudManager;
    }

    public HypixelManager getHypixelManager() {
        return hypixelManager;
    }

}