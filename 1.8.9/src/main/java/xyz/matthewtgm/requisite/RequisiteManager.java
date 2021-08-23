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
import xyz.matthewtgm.requisite.core.IEventListener;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.IRequisiteManager;
import xyz.matthewtgm.requisite.core.RequisiteEventManager;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.FileManager;
import xyz.matthewtgm.requisite.core.hud.HudRegistry;
import xyz.matthewtgm.requisite.core.integration.IModIntegration;
import xyz.matthewtgm.requisite.core.keybinds.KeyBindRegistry;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.notifications.INotifications;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.core.util.messages.IMessageQueue;
import xyz.matthewtgm.requisite.gui.RequisiteMenu;
import xyz.matthewtgm.requisite.core.hypixel.HypixelHelper;
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
    private RequisiteEventListener internalEventListener;
    private RequisiteClientSocket socket;

    private KeyBindRegistry keyBindRegistry;
    private HudRegistry hudRegistry;

    private EnhancedFontRenderer enhancedFontRenderer;
    private PlayerHelper playerHelper;
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
    private PositionHelper positionHelper;
    private RomanNumeral romanNumerals;
    private HypixelHelper hypixelHelper;
    private RenderHelper renderHelper;
    private StringHelper stringHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;
    private MojangAPI mojangApi;

    /* 1.8.9-specific utilities. */
    private GlHelper glHelper;
    private GuiHelper guiHelper;

    public void initialize(IRequisite requisite, File gameDirectory) {
        if (initialized)
            return;

        logger = LogManager.getLogger("Requisite");
        eventBus = new SimpleEventBus();
        fileManager = new FileManager();
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        modIntegration = new ModIntegration(requisite);
        internalEventManager = new RequisiteEventManager(requisite);
        internalEventListener = new RequisiteEventListener(requisite);
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);

        keyBindRegistry = new KeyBindRegistry(requisite);
        hudRegistry = new HudRegistry(requisite);

        enhancedFontRenderer = new EnhancedFontRenderer(requisite);
        playerHelper = new PlayerHelper();
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
        positionHelper = new PositionHelper();
        romanNumerals = new RomanNumeral();
        hypixelHelper = new HypixelHelper(requisite);
        renderHelper = new RenderHelper();
        stringHelper = new StringHelper();
        messageQueue = new MessageQueue(requisite);
        serverHelper = new ServerHelper();
        mojangApi = new MojangAPI();

        glHelper = new GlHelper();
        guiHelper = new GuiHelper();

        MinecraftForge.EVENT_BUS.register(new RequisiteEventListener(requisite));

        initialized = true;
    }

    public boolean initialized() {
        return initialized;
    }

    public Logger getLogger() {
        checkInitialized();
        return logger;
    }

    public SimpleEventBus getEventBus() {
        checkInitialized();
        return eventBus;
    }

    public FileManager getFileManager() {
        checkInitialized();
        return fileManager;
    }

    public ConfigurationManager getConfigurationManager() {
        checkInitialized();
        return configurationManager;
    }

    public IModIntegration getModIntegration() {
        checkInitialized();
        return modIntegration;
    }

    public RequisiteEventManager getInternalEventManager() {
        checkInitialized();
        return internalEventManager;
    }

    public IEventListener getInternalEventListener() {
        checkInitialized();
        return internalEventListener;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        checkInitialized();
        return socket;
    }

    public void openMenu() {
        checkInitialized();
        guiHelper.open(new RequisiteMenu());
    }

    public KeyBindRegistry getKeyBindRegistry() {
        checkInitialized();
        return keyBindRegistry;
    }

    public HudRegistry getHudRegistry() {
        checkInitialized();
        return hudRegistry;
    }

    public EnhancedFontRenderer getEnhancedFontRenderer() {
        checkInitialized();
        return enhancedFontRenderer;
    }

    public IPlayerHelper getPlayerHelper() {
        checkInitialized();
        return playerHelper;
    }

    public IChatHelper getChatHelper() {
        checkInitialized();
        return chatHelper;
    }

    public ColourHelper getColourHelper() {
        checkInitialized();
        return colourHelper;
    }

    public LoggingHelper getLoggingHelper() {
        checkInitialized();
        return loggingHelper;
    }

    public UniversalLogger getUniversalLogger() {
        checkInitialized();
        return universalLogger;
    }

    public ClipboardHelper getClipboardHelper() {
        checkInitialized();
        return clipboardHelper;
    }

    public DateHelper getDateHelper() {
        checkInitialized();
        return dateHelper;
    }

    public EasingHelper getEasingHelper() {
        checkInitialized();
        return easingHelper;
    }

    public MathHelper getMathHelper() {
        checkInitialized();
        return mathHelper;
    }

    public IMouseHelper getMouseHelper() {
        checkInitialized();
        return mouseHelper;
    }

    public Multithreading getMultithreading() {
        checkInitialized();
        return multithreading;
    }

    public INotifications getNotifications() {
        checkInitialized();
        return notifications;
    }

    public ReflectionHelper getReflectionHelper() {
        checkInitialized();
        return reflectionHelper;
    }

    public IPositionHelper getPositionHelper() {
        return positionHelper;
    }

    public RomanNumeral getRomanNumerals() {
        checkInitialized();
        return romanNumerals;
    }

    public HypixelHelper getHypixelHelper() {
        checkInitialized();
        return hypixelHelper;
    }

    public IRenderHelper getRenderHelper() {
        checkInitialized();
        return renderHelper;
    }

    public IStringHelper getStringHelper() {
        checkInitialized();
        return stringHelper;
    }

    public IMessageQueue getMessageQueue() {
        checkInitialized();
        return messageQueue;
    }

    public IServerHelper getServerHelper() {
        checkInitialized();
        return serverHelper;
    }

    public MojangAPI getMojangApi() {
        checkInitialized();
        return mojangApi;
    }

    public GlHelper getGlHelper() {
        checkInitialized();
        return glHelper;
    }

    public GuiHelper getGuiHelper() {
        checkInitialized();
        return guiHelper;
    }

}