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

package xyz.deftu.requisite;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.common.Mod;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.RequisiteEventManager;
import xyz.deftu.requisite.core.RequisiteInfo;
import xyz.deftu.requisite.core.cosmetics.CosmeticManager;
import xyz.deftu.requisite.core.files.ConfigurationManager;
import xyz.deftu.requisite.core.files.FileManager;
import xyz.deftu.requisite.core.hud.HudRegistry;
import xyz.deftu.requisite.core.hypixel.HypixelHelper;
import xyz.deftu.requisite.core.keybinds.KeyBindRegistry;
import xyz.deftu.requisite.core.networking.RequisiteClientSocket;
import xyz.deftu.requisite.core.util.*;
import xyz.deftu.requisite.cosmetics.CosmeticInitializer;
import xyz.deftu.requisite.integration.ModIntegration;
import xyz.deftu.requisite.networking.SocketHelper;
import xyz.deftu.requisite.notifications.Notifications;
import xyz.deftu.requisite.rendering.EnhancedFontRenderer;
import xyz.deftu.requisite.util.*;

import java.io.File;

@Mod(
        name = RequisiteInfo.NAME,
        version = RequisiteInfo.VER,
        modid = RequisiteInfo.ID,
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Requisite implements IRequisite {

    /* Constants. */
    private static final Requisite INSTANCE = new Requisite();

    /* Services. */
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private CosmeticManager<AbstractClientPlayer> cosmeticManager;
    private ModIntegration modIntegration;
    private RequisiteEventManager internalEventManager;
    private RequisiteEventListener internalEventListener;
    private RequisiteClientSocket requisiteSocket;

    /* Utilities. */
    private KeyBindRegistry keyBindRegistry;
    private HudRegistry hudRegistry;

    private EnhancedFontRenderer enhancedFontRenderer;
    private PlayerHelper playerHelper;
    private ChatHelper chatHelper;
    private UniversalLogger universalLogger;
    private MouseHelper mouseHelper;
    private Notifications notifications;
    private PositionHelper positionHelper;
    private HypixelHelper hypixelHelper;
    private RenderHelper renderHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;

    /* Version-dependant utilities. */
    private GlHelper glHelper;

    public void initialize(File gameDir) {
        /* Initialize services. */
        fileManager = new FileManager(this);
        configurationManager = new ConfigurationManager("config", fileManager.getRequisiteModDirectory(fileManager.getRequisiteDirectory(fileManager.getConfigDirectory(gameDir))));
        cosmeticManager = new CosmeticManager<>(new CosmeticInitializer()); // TODO: 2021/09/09 finish. 
        cosmeticManager.initialize();
        modIntegration = new ModIntegration(this);
        internalEventManager = new RequisiteEventManager(this);
        internalEventListener = new RequisiteEventListener(this);
        requisiteSocket = new RequisiteClientSocket(this, new SocketHelper());
        boolean socketConnected = requisiteSocket.awaitConnect();

        /* Initialize utilities. */
        keyBindRegistry = new KeyBindRegistry(this);
        hudRegistry = new HudRegistry(this);

        enhancedFontRenderer = new EnhancedFontRenderer(this);
        playerHelper = new PlayerHelper();
        chatHelper = new ChatHelper();
        universalLogger = new UniversalLogger(this);
        mouseHelper = new MouseHelper();
        notifications = new Notifications(this);
        positionHelper = new PositionHelper();
        hypixelHelper = new HypixelHelper(this);
        renderHelper = new RenderHelper();
        messageQueue = new MessageQueue(this);
        serverHelper = new ServerHelper();

        if (!socketConnected) {
            notifications.push("Error!", "Failed to connect to Requisite WebSocket. " + ChatColour.BOLD + "Click to try a reconnect.", notification -> {
                boolean socketReconnected = requisiteSocket.awaitReconnect();
                if (!socketReconnected) {
                    notifications.push(notification.clone());
                    notification.close();
                }
            });
        }

        /* Initialize version-dependant utilities. */
        glHelper = new GlHelper();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public CosmeticManager<AbstractClientPlayer> getCosmeticManager() {
        return cosmeticManager;
    }

    public ModIntegration getModIntegration() {
        return modIntegration;
    }

    public RequisiteEventManager getInternalEventManager() {
        return internalEventManager;
    }

    public RequisiteEventListener getInternalEventListener() {
        return internalEventListener;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return requisiteSocket;
    }

    public void openMenu() {}

    public KeyBindRegistry getKeyBindRegistry() {
        return keyBindRegistry;
    }

    public HudRegistry getHudRegistry() {
        return hudRegistry;
    }

    public EnhancedFontRenderer getEnhancedFontRenderer() {
        return enhancedFontRenderer;
    }

    public PlayerHelper getPlayerHelper() {
        return playerHelper;
    }

    public ChatHelper getChatHelper() {
        return chatHelper;
    }

    public UniversalLogger getUniversalLogger() {
        return universalLogger;
    }

    public MouseHelper getMouseHelper() {
        return mouseHelper;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public PositionHelper getPositionHelper() {
        return positionHelper;
    }

    public HypixelHelper getHypixelHelper() {
        return hypixelHelper;
    }

    public RenderHelper getRenderHelper() {
        return renderHelper;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    public ServerHelper getServerHelper() {
        return serverHelper;
    }

    public GlHelper getGlHelper() {
        return glHelper;
    }

    public static Requisite getInstance() {
        return INSTANCE;
    }

}