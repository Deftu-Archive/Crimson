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

import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.matthewtgm.requisite.commands.CommandRegistry;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.IRequisiteManager;
import xyz.matthewtgm.requisite.core.commands.ICommandRegistry;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.FileManager;
import xyz.matthewtgm.requisite.core.keybinds.KeyBindRegistry;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.notifications.INotifications;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.core.util.messages.IMessageQueue;
import xyz.matthewtgm.requisite.hypixel.HypixelManager;
import xyz.matthewtgm.requisite.notifications.Notifications;
import xyz.matthewtgm.requisite.rendering.EnhancedFontRenderer;
import xyz.matthewtgm.requisite.util.*;
import xyz.matthewtgm.simpleeventbus.SimpleEventBus;
import xyz.matthewtgm.tgmconfig.Configuration;

import java.io.File;

public class RequisiteManager implements IRequisiteManager {

    private Logger logger;
    private SimpleEventBus eventBus;
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private CommandRegistry commandRegistry;
    private RequisiteClientSocket socket;

    private KeyBindRegistry keyBindRegistry;
    private EnhancedFontRenderer enhancedFontRenderer;
    private ChatHelper chatHelper;
    private ColourHelper colourHelper;
    private LoggingHelper loggingHelper;
    private UniversalLogger universalLogger;
    private ClipboardHelper clipboardHelper;
    private DateHelper dateHelper;
    private EasingHelper easingHelper;
    private MathHelper mathHelper;
    private MouseHelper mouseHelper;
    private Multithreading multithreading;
    private Notifications notifications;
    private ObjectHelper objectHelper;
    private ReflectionHelper reflectionHelper;
    private RomanNumeral romanNumerals;
    private RenderHelper renderHelper;
    private StringHelper stringHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;
    private MojangAPI mojangApi;

    /* 1.8.9-specific utilities. */
    private GlHelper glHelper;
    private HypixelManager hypixelManager;

    public void initialize(IRequisite requisite, File gameDirectory) {
        logger = LogManager.getLogger("Requisite");
        eventBus = new SimpleEventBus();
        fileManager = new FileManager();
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        commandRegistry = new CommandRegistry();
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);

        keyBindRegistry = new KeyBindRegistry(requisite);
        enhancedFontRenderer = new EnhancedFontRenderer(requisite);
        chatHelper = new ChatHelper();
        colourHelper = new ColourHelper();
        loggingHelper = new LoggingHelper();
        universalLogger = new UniversalLogger(requisite);
        clipboardHelper = new ClipboardHelper();
        dateHelper = new DateHelper();
        easingHelper = new EasingHelper();
        mathHelper = new MathHelper();
        mouseHelper = new MouseHelper();
        multithreading = new Multithreading();
        notifications = new Notifications((Requisite) requisite);
        objectHelper = new ObjectHelper();
        reflectionHelper = new ReflectionHelper();
        romanNumerals = new RomanNumeral();
        renderHelper = new RenderHelper();
        stringHelper = new StringHelper();
        messageQueue = new MessageQueue(requisite);
        serverHelper = new ServerHelper();
        mojangApi = new MojangAPI();

        glHelper = new GlHelper();
        hypixelManager = new HypixelManager(requisite);

        MinecraftForge.EVENT_BUS.register(new RequisiteEventListener());
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

    public ICommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return socket;
    }

    public void openMenu() {
        universalLogger.info("Tried to open menu LMFAOOO");
    }

    public KeyBindRegistry getKeyBindRegistry() {
        return keyBindRegistry;
    }

    public EnhancedFontRenderer getEnhancedFontRenderer() {
        return enhancedFontRenderer;
    }

    public IChatHelper getChatHelper() {
        return chatHelper;
    }

    public ColourHelper getColourHelper() {
        return colourHelper;
    }

    public LoggingHelper getLoggingHelper() {
        return loggingHelper;
    }

    public UniversalLogger getUniversalLogger() {
        return universalLogger;
    }

    public ClipboardHelper getClipboardHelper() {
        return clipboardHelper;
    }

    public DateHelper getDateHelper() {
        return dateHelper;
    }

    public EasingHelper getEasingHelper() {
        return easingHelper;
    }

    public MathHelper getMathHelper() {
        return mathHelper;
    }

    public IMouseHelper getMouseHelper() {
        return mouseHelper;
    }

    public Multithreading getMultithreading() {
        return multithreading;
    }

    public INotifications getNotifications() {
        return notifications;
    }

    public ObjectHelper getObjectHelper() {
        return objectHelper;
    }

    public ReflectionHelper getReflectionHelper() {
        return reflectionHelper;
    }

    public RomanNumeral getRomanNumerals() {
        return romanNumerals;
    }

    public IRenderHelper getRenderHelper() {
        return renderHelper;
    }

    public IStringHelper getStringHelper() {
        return stringHelper;
    }

    public IMessageQueue getMessageQueue() {
        return messageQueue;
    }

    public IServerHelper getServerHelper() {
        return serverHelper;
    }

    public MojangAPI getMojangApi() {
        return mojangApi;
    }

    public GlHelper getGlHelper() {
        return glHelper;
    }

    public HypixelManager getHypixelManager() {
        return hypixelManager;
    }

}