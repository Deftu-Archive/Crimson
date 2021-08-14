package xyz.matthewtgm.requisite.core;

import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.FileManager;
import xyz.matthewtgm.requisite.core.keybinds.KeyBindRegistry;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.notifications.INotifications;
import xyz.matthewtgm.requisite.core.util.ObjectHelper;
import xyz.matthewtgm.requisite.core.rendering.IEnhancedFontRenderer;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.core.util.messages.IMessageQueue;
import xyz.matthewtgm.simpleeventbus.SimpleEventBus;

public interface IRequisiteManager {

    /* Requisite services. */
    SimpleEventBus getEventBus();
    FileManager getFileManager();
    ConfigurationManager getConfigurationManager();
    RequisiteClientSocket getRequisiteSocket();

    /* Utilities. */
    KeyBindRegistry getKeyBindRegistry();

    IEnhancedFontRenderer getEnhancedFontRenderer();
    IChatHelper getChatHelper();
    ColourHelper getColourHelper();
    ClipboardHelper getClipboardHelper();
    DateHelper getDateHelper();
    EasingHelper getEasingHelper();
    MathHelper getMathHelper();
    IMouseHelper getMouseHelper();
    Multithreading getMultithreading();
    INotifications getNotifications();
    ObjectHelper getObjectHelper();
    ReflectionHelper getReflectionHelper();
    RomanNumeral getRomanNumerals();
    IRenderHelper getRenderHelper();
    IStringHelper getStringHelper();
    IMessageQueue getMessageQueue();
    IServerHelper getServerHelper();
    MojangAPI getMojangApi();

    void initialize(IRequisite requisite);

}