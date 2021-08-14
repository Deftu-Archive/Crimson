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
import xyz.matthewtgm.requisite.rendering.EnhancedFontRenderer;
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

    public void initialize(IRequisite requisite, File gameDirectory) {
        eventBus = new SimpleEventBus();
        fileManager = new FileManager();
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);
        cosmeticManager = new CosmeticManager(requisite);

        keyBindRegistry = new KeyBindRegistry(requisite);
        enhancedFontRenderer = new EnhancedFontRenderer();
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
        return null;
    }

    public ColourHelper getColourHelper() {
        return null;
    }

    public ClipboardHelper getClipboardHelper() {
        return null;
    }

    public DateHelper getDateHelper() {
        return null;
    }

    public EasingHelper getEasingHelper() {
        return null;
    }

    public MathHelper getMathHelper() {
        return null;
    }

    public IMouseHelper getMouseHelper() {
        return null;
    }

    public Multithreading getMultithreading() {
        return null;
    }

    public INotifications getNotifications() {
        return null;
    }

    public ObjectHelper getObjectHelper() {
        return null;
    }

    public ReflectionHelper getReflectionHelper() {
        return null;
    }

    public RomanNumeral getRomanNumerals() {
        return null;
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
        return null;
    }

}