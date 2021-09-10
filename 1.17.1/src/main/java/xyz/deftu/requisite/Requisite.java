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

import net.minecraft.client.network.AbstractClientPlayerEntity;
import xyz.deftu.mango.exceptions.UnfinishedApiException;
import xyz.deftu.requisite.commands.CommandHelper;
import xyz.deftu.requisite.core.IEventListener;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.RequisiteEventManager;
import xyz.deftu.requisite.core.commands.CommandRegistry;
import xyz.deftu.requisite.core.cosmetics.CosmeticManager;
import xyz.deftu.requisite.core.files.ConfigurationManager;
import xyz.deftu.requisite.core.files.FileManager;
import xyz.deftu.requisite.core.hypixel.HypixelHelper;
import xyz.deftu.requisite.core.integration.IModIntegration;
import xyz.deftu.requisite.core.networking.RequisiteClientSocket;
import xyz.deftu.requisite.core.notifications.INotifications;
import xyz.deftu.requisite.core.rendering.IEnhancedFontRenderer;
import xyz.deftu.requisite.core.util.*;
import xyz.deftu.requisite.core.util.messages.IMessageQueue;
import xyz.deftu.requisite.integration.ModIntegration;
import xyz.deftu.requisite.networking.SocketHelper;
import xyz.deftu.requisite.notifications.Notifications;
import xyz.deftu.requisite.rendering.EnhancedFontRenderer;
import xyz.deftu.requisite.util.*;

import java.io.File;

public class Requisite implements IRequisite {

    /* Constants. */
    private static final Requisite INSTANCE = new Requisite();
    private static boolean initialized;

    /* Services. */
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private CosmeticManager<AbstractClientPlayerEntity> cosmeticManager;
    private ModIntegration modIntegration;
    private CommandRegistry commandRegistry;
    private RequisiteEventManager internalEventManager;
    private RequisiteClientSocket requisiteSocket;

    /* Utilities. */
    private EnhancedFontRenderer enhancedFontRenderer;
    private PlayerHelper playerHelper;
    private ChatHelper chatHelper;
    private UniversalLogger universalLogger;
    private MouseHelper mouseHelper;
    private Notifications notifications;
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
        configurationManager = new ConfigurationManager("config", fileManager.getRequisiteModDirectory(fileManager.getRequisiteDirectory(fileManager.getConfigDirectory(gameDir))));
        //cosmeticManager = new CosmeticManager<>(new CosmeticInitializer());
        //cosmeticManager.initialize();
        modIntegration = new ModIntegration(this);
        commandRegistry = new CommandRegistry(this, new CommandHelper());
        internalEventManager = new RequisiteEventManager(this);
        requisiteSocket = new RequisiteClientSocket(this, new SocketHelper());
        boolean socketConnected = requisiteSocket.awaitConnect();

        /* Initialize utilities. */
        enhancedFontRenderer = new EnhancedFontRenderer(this);
        playerHelper = new PlayerHelper();
        chatHelper = new ChatHelper();
        universalLogger = new UniversalLogger(this);
        mouseHelper = new MouseHelper();
        notifications = new Notifications(this);
        //positionHelper = new PositionHelper();
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

    public CosmeticManager<AbstractClientPlayerEntity> getCosmeticManager() {
        return cosmeticManager;
    }

    public ModIntegration getModIntegration() {
        return modIntegration;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public RequisiteEventManager getInternalEventManager() {
        return internalEventManager;
    }

    public IEventListener getInternalEventListener() {
        throw new UnsupportedOperationException(name() + " 1.17.1 does not support the internal event listener.");
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return requisiteSocket;
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

    public IPositionHelper getPositionHelper() {
        throw new UnfinishedApiException();
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

    public static boolean isInitialized() {
        return initialized;
    }

}