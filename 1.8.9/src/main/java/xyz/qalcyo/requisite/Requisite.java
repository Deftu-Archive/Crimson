/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
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

package xyz.qalcyo.requisite;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.common.Mod;
import xyz.qalcyo.requisite.commands.CommandHelper;
import xyz.qalcyo.requisite.core.integration.ModMetadata;
import xyz.qalcyo.requisite.core.keybinds.KeyBindRegistry;
import xyz.qalcyo.requisite.cosmetics.CosmeticHelper;
import xyz.qalcyo.requisite.cosmetics.CosmeticInitializer;
import xyz.qalcyo.requisite.util.*;
import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.RequisiteEventManager;
import xyz.qalcyo.requisite.core.RequisiteInfo;
import xyz.qalcyo.requisite.core.commands.CommandRegistry;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticManager;
import xyz.qalcyo.requisite.core.files.ConfigurationManager;
import xyz.qalcyo.requisite.core.files.FileManager;
import xyz.qalcyo.requisite.core.hypixel.HypixelHelper;
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket;
import xyz.qalcyo.requisite.core.util.ChatColour;
import xyz.qalcyo.requisite.core.util.UniversalLogger;
import xyz.qalcyo.requisite.integration.ModIntegration;
import xyz.qalcyo.requisite.networking.SocketHelper;
import xyz.qalcyo.requisite.notifications.Notifications;
import xyz.qalcyo.requisite.rendering.EnhancedFontRenderer;

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
    private static boolean initialized;

    /* Services. */
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private CosmeticManager<AbstractClientPlayer> cosmeticManager;
    private ModIntegration modIntegration;
    private CommandRegistry commandRegistry;
    private KeyBindRegistry keyBindRegistry;
    private RequisiteEventManager internalEventManager;
    private RequisiteEventListener internalEventListener;
    private RequisiteClientSocket requisiteSocket;

    /* Utilities. */
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

    public boolean initialize(File gameDir) {
        if (initialized)
            return false;

        /* Initialize services. */
        fileManager = new FileManager(this);
        configurationManager = new ConfigurationManager("config", fileManager.getRequisiteDirectory(fileManager.getQalcyoDirectory(fileManager.getConfigDirectory(gameDir))));
        requisiteSocket = new RequisiteClientSocket(this, new SocketHelper());
        boolean socketConnected = requisiteSocket.awaitConnect();
        cosmeticManager = new CosmeticManager<>(this, new CosmeticInitializer(), new CosmeticHelper());
        cosmeticManager.initialize();
        modIntegration = new ModIntegration(this);
        commandRegistry = new CommandRegistry(this, new CommandHelper());
        keyBindRegistry = new KeyBindRegistry(this);
        internalEventManager = new RequisiteEventManager(this);
        internalEventListener = new RequisiteEventListener(this);

        /* Initialize utilities. */
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

        return initialized = true;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return requisiteSocket;
    }

    public CosmeticManager<AbstractClientPlayer> getCosmeticManager() {
        return cosmeticManager;
    }

    public ModIntegration getModIntegration() {
        return modIntegration;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public KeyBindRegistry getKeyBindRegistry() {
        return keyBindRegistry;
    }

    public RequisiteEventManager getInternalEventManager() {
        return internalEventManager;
    }

    public RequisiteEventListener getInternalEventListener() {
        return internalEventListener;
    }

    public void openMenu() {

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

    public ModMetadata metadata() {
        return ModMetadata.from(name(), version())
                .setCommand("/requisite");
    }

    public static Requisite getInstance() {
        return INSTANCE;
    }

    public static boolean isInitialized() {
        return initialized;
    }

}