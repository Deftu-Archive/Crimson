package xyz.matthewtgm.requisite.core;

import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;
import xyz.matthewtgm.requisite.core.cosmetics.CosmeticManager;
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

import java.io.File;
import java.net.URI;
import java.util.Base64;

public interface IRequisiteManager {

    /* Initialize Requisite services. */
    void initialize(IRequisite requisite, File gameDirectory);

    /* Requisite services. */
    SimpleEventBus getEventBus();
    FileManager getFileManager();
    ConfigurationManager getConfigurationManager();
    RequisiteClientSocket getRequisiteSocket();

    CosmeticManager getCosmeticManager();

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

    /* Default. */
    default URI fetchSocketUri() {
        JsonObject object = JsonApiHelper.getJsonObject("https://raw.githubusercontent.com/TGMDevelopment/RequisiteData/main/websocket.json", true);
        String encoded = object.getAsString("uri");
        for (int i = 0; i < object.getAsInt("loop"); i++)
            encoded = new String(Base64.getDecoder().decode(encoded));
        return URI.create(encoded);
    }

}