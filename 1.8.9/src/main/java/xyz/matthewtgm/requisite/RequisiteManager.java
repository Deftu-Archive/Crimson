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

import net.minecraftforge.common.*;
import xyz.matthewtgm.requisite.core.*;
import xyz.matthewtgm.requisite.core.commands.CommandRegistry;
import xyz.matthewtgm.requisite.core.files.*;
import xyz.matthewtgm.requisite.core.hud.*;
import xyz.matthewtgm.requisite.core.integration.*;
import xyz.matthewtgm.requisite.core.keybinds.*;
import xyz.matthewtgm.requisite.core.networking.*;
import xyz.matthewtgm.requisite.core.notifications.*;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.core.util.messages.*;
import xyz.matthewtgm.requisite.gui.*;
import xyz.matthewtgm.requisite.core.hypixel.*;
import xyz.matthewtgm.requisite.integration.ModIntegration;
import xyz.matthewtgm.requisite.notifications.Notifications;
import xyz.matthewtgm.requisite.rendering.EnhancedFontRenderer;
import xyz.matthewtgm.requisite.util.*;
import xyz.matthewtgm.tgmconfig.*;

import java.io.File;

public class RequisiteManager implements IRequisiteManager {

    private boolean initialized;

    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private ModIntegration modIntegration;
    private RequisiteEventManager internalEventManager;
    private RequisiteEventListener internalEventListener;
    private CommandRegistry commandRegistry;
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
    private StringHelper stringHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;

    /* 1.8.9-specific utilities. */
    private GlHelper glHelper;
    private GuiHelper guiHelper;

    public void initialize(IRequisite requisite, File gameDirectory) {
        if (initialized)
            return;

        fileManager = new FileManager(requisite);
        configurationManager = new ConfigurationManager(new Configuration(new File(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory))), requisite.name())));
        modIntegration = new ModIntegration(requisite);
        internalEventManager = new RequisiteEventManager(requisite);
        internalEventListener = new RequisiteEventListener(requisite);
        commandRegistry = new CommandRegistry(requisite);
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);

        configurationManager.addConfigurable(keyBindRegistry = new KeyBindRegistry(requisite));
        configurationManager.addConfigurable(hudRegistry = new HudRegistry(requisite));

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
        stringHelper = new StringHelper();
        messageQueue = new MessageQueue(requisite);
        serverHelper = new ServerHelper();

        glHelper = new GlHelper();
        guiHelper = new GuiHelper();

        MinecraftForge.EVENT_BUS.register(new RequisiteEventListener(requisite));

        initialized = true;
    }

    public boolean initialized() {
        return initialized;
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
        return internalEventListener;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return socket;
    }

    public void openMenu() {
        guiHelper.open(new RequisiteMenu());
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

    public IChatHelper getChatHelper() {
        return chatHelper;
    }

    public UniversalLogger getUniversalLogger() {
        return universalLogger;
    }

    public IMouseHelper getMouseHelper() {
        return mouseHelper;
    }

    public INotifications getNotifications() {
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

    public IRenderHelper getRenderHelper() {
        return renderHelper;
    }

    public StringHelper getStringHelper() {
        return stringHelper;
    }

    public IMessageQueue getMessageQueue() {
        return messageQueue;
    }

    public IServerHelper getServerHelper() {
        return serverHelper;
    }

    public GlHelper getGlHelper() {
        return glHelper;
    }

    public GuiHelper getGuiHelper() {
        return guiHelper;
    }

}