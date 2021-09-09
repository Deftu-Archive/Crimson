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

import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.deftu.requisite.core.IEventListener;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.RequisiteEventManager;
import xyz.deftu.requisite.core.util.IPlayerHelper;
import xyz.deftu.requisite.core.util.IPositionHelper;
import xyz.deftu.requisite.core.util.IServerHelper;
import xyz.deftu.requisite.core.util.UniversalLogger;
import xyz.deftu.requisite.util.*;
import xyz.matthewtgm.requisite.core.commands.CommandRegistry;
import xyz.deftu.requisite.core.files.ConfigurationManager;
import xyz.deftu.requisite.core.files.FileManager;
import xyz.deftu.requisite.core.hud.HudRegistry;
import xyz.deftu.requisite.core.hypixel.HypixelHelper;
import xyz.deftu.requisite.core.integration.IModIntegration;
import xyz.deftu.requisite.core.keybinds.KeyBindRegistry;
import xyz.deftu.requisite.core.networking.RequisiteClientSocket;
import xyz.deftu.requisite.integration.ModIntegration;
import xyz.deftu.requisite.notifications.Notifications;
import xyz.deftu.requisite.rendering.EnhancedFontRenderer;
import xyz.matthewtgm.requisite.util.*;
import xyz.matthewtgm.simpleeventbus.SimpleEventBus;
import xyz.matthewtgm.tgmconfig.Configuration;

import java.io.File;

public class RequisiteManager implements IRequisiteManager {

    private boolean initialized;

    private Logger logger;
    private SimpleEventBus eventBus;
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private ModIntegration modIntegration;
    private RequisiteEventManager internalEventManager;
    private RequisiteClientSocket socket;

    private KeyBindRegistry keyBindRegistry;
    private HudRegistry hudRegistry;

    private EnhancedFontRenderer enhancedFontRenderer;
    private PlayerHelper playerHelper;
    private ChatHelper chatHelper;
    private UniversalLogger universalLogger;
    private MouseHelper mouseHelper;
    private Notifications notifications;
    private PositionHelper positionHelper;
    private ExternalModHelper externalModHelper;
    private HypixelHelper hypixelHelper;
    private RenderHelper renderHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;

    private GlHelper glHelper;

    public void initialize(IRequisite requisite, File gameDirectory) {
        logger = LogManager.getLogger("Requisite");
        eventBus = new SimpleEventBus();
        fileManager = new FileManager(requisite);
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteModDirectory(fileManager.getRequisiteDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        modIntegration = new ModIntegration(requisite);
        internalEventManager = new RequisiteEventManager(requisite);
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);

        keyBindRegistry = new KeyBindRegistry(requisite);
        hudRegistry = new HudRegistry(requisite);

        enhancedFontRenderer = new EnhancedFontRenderer(requisite);
        playerHelper = new PlayerHelper();
        chatHelper = new ChatHelper();
        universalLogger = new UniversalLogger(requisite);
        mouseHelper = new MouseHelper();
        notifications = new Notifications((Requisite) requisite);
        positionHelper = new PositionHelper();
        externalModHelper = new ExternalModHelper(requisite);
        hypixelHelper = new HypixelHelper(requisite);
        renderHelper = new RenderHelper();
        messageQueue = new MessageQueue(requisite);
        serverHelper = new ServerHelper();

        glHelper = new GlHelper();

        initialized = true;
    }

    public boolean initialized() {
        return initialized;
    }

    public Logger getLogger() {
        return logger;
    }

    public SimpleEventBus getEventBus() {
        return eventBus;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public IModIntegration getModIntegration() {
        return modIntegration;
    }

    public RequisiteEventManager getInternalEventManager() {
        return internalEventManager;
    }

    public IEventListener getInternalEventListener() {
        throw new UnsupportedOperationException("1.17.1/Fabric version does not support the internal event listener.");
    }

    public CommandRegistry getCommandRegistry() {
        return null;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return socket;
    }

    public void openMenu() {
        MinecraftClient.getInstance().setScreen(null);
    }

    public KeyBindRegistry getKeyBindRegistry() {
        return keyBindRegistry;
    }

    public HudRegistry getHudRegistry() {
        return hudRegistry;
    }

    public EnhancedFontRenderer getEnhancedFontRenderer() {
        return enhancedFontRenderer;
    }

    public IPlayerHelper getPlayerHelper() {
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
        return positionHelper;
    }

    public ExternalModHelper getExternalModHelper() {
        return externalModHelper;
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

    public IServerHelper getServerHelper() {
        return serverHelper;
    }

    public GlHelper getGlHelper() {
        return glHelper;
    }

}