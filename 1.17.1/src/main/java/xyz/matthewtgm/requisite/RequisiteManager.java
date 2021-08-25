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

import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.matthewtgm.requisite.core.IEventListener;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.IRequisiteManager;
import xyz.matthewtgm.requisite.core.RequisiteEventManager;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.FileManager;
import xyz.matthewtgm.requisite.core.hud.HudRegistry;
import xyz.matthewtgm.requisite.core.hypixel.HypixelHelper;
import xyz.matthewtgm.requisite.core.integration.IModIntegration;
import xyz.matthewtgm.requisite.core.keybinds.KeyBindRegistry;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.integration.ModIntegration;
import xyz.matthewtgm.requisite.notifications.Notifications;
import xyz.matthewtgm.requisite.rendering.EnhancedFontRenderer;
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
    private ReflectionHelper reflectionHelper;
    private RomanNumeral romanNumerals;
    private RenderHelper renderHelper;
    private StringHelper stringHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;
    private MojangAPI mojangApi;

    private GlHelper glHelper;

    public void initialize(IRequisite requisite, File gameDirectory) {
        logger = LogManager.getLogger("Requisite");
        eventBus = new SimpleEventBus();
        fileManager = new FileManager(requisite);
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        modIntegration = new ModIntegration(requisite);
        internalEventManager = new RequisiteEventManager(requisite);
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
        reflectionHelper = new ReflectionHelper();
        romanNumerals = new RomanNumeral();
        renderHelper = new RenderHelper();
        stringHelper = new StringHelper();
        messageQueue = new MessageQueue(requisite);
        serverHelper = new ServerHelper();
        mojangApi = new MojangAPI();

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
        return null;
    }

    public EnhancedFontRenderer getEnhancedFontRenderer() {
        return enhancedFontRenderer;
    }

    public IPlayerHelper getPlayerHelper() {
        return null;
    }

    public ChatHelper getChatHelper() {
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

    public MouseHelper getMouseHelper() {
        return mouseHelper;
    }

    public Multithreading getMultithreading() {
        return multithreading;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public ReflectionHelper getReflectionHelper() {
        return reflectionHelper;
    }

    public IPositionHelper getPositionHelper() {
        return null;
    }

    public RomanNumeral getRomanNumerals() {
        return romanNumerals;
    }

    public HypixelHelper getHypixelHelper() {
        return null;
    }

    public RenderHelper getRenderHelper() {
        return renderHelper;
    }

    public StringHelper getStringHelper() {
        return stringHelper;
    }

    public MessageQueue getMessageQueue() {
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

}