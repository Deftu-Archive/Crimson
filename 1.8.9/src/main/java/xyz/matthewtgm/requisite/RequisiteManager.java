package xyz.matthewtgm.requisite;

import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.IRequisiteManager;
import xyz.matthewtgm.requisite.core.cosmetics.CosmeticManager;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.FileManager;
import xyz.matthewtgm.requisite.core.keybinds.KeyBindRegistry;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.notifications.INotifications;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.core.util.messages.IMessageQueue;
import xyz.matthewtgm.requisite.notifications.Notifications;
import xyz.matthewtgm.requisite.rendering.EnhancedFontRenderer;
import xyz.matthewtgm.requisite.util.ChatHelper;
import xyz.matthewtgm.requisite.util.GlHelper;
import xyz.matthewtgm.requisite.util.MouseHelper;
import xyz.matthewtgm.simpleeventbus.SimpleEventBus;
import xyz.matthewtgm.tgmconfig.Configuration;

import java.io.File;

public class RequisiteManager implements IRequisiteManager {

    private SimpleEventBus eventBus;
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private RequisiteClientSocket socket;
    private CosmeticManager cosmeticManager;

    private KeyBindRegistry keyBindRegistry;
    private EnhancedFontRenderer enhancedFontRenderer;
    private ChatHelper chatHelper;
    private ColourHelper colourHelper;
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



    private MojangAPI mojangApi;

    /* 1.8.9-specific utilities. */
    private GlHelper glHelper;

    public void initialize(IRequisite requisite, File gameDirectory) {
        eventBus = new SimpleEventBus();
        fileManager = new FileManager();
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);
        cosmeticManager = new CosmeticManager(requisite);

        keyBindRegistry = new KeyBindRegistry(requisite);
        enhancedFontRenderer = new EnhancedFontRenderer(requisite);
        chatHelper = new ChatHelper();
        colourHelper = new ColourHelper();
        clipboardHelper = new ClipboardHelper();
        dateHelper = new DateHelper();
        easingHelper = new EasingHelper();
        mathHelper = new MathHelper();
        mouseHelper = new MouseHelper();
        multithreading = new Multithreading();
        notifications = new Notifications((Requisite) requisite);
        notifications.push("Hello,", "world!");
        objectHelper = new ObjectHelper();
        reflectionHelper = new ReflectionHelper();
        romanNumerals = new RomanNumeral();



        mojangApi = new MojangAPI();

        glHelper = new GlHelper();
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

    public RequisiteClientSocket getRequisiteSocket() {
        return socket;
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
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
        return null;
    }

    public IStringHelper getStringHelper() {
        return null;
    }

    public IMessageQueue getMessageQueue() {
        return null;
    }

    public IServerHelper getServerHelper() {
        return null;
    }

    public MojangAPI getMojangApi() {
        return mojangApi;
    }

    public GlHelper getGlHelper() {
        return glHelper;
    }

}