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

package xyz.matthewtgm.requisite.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;
import xyz.matthewtgm.requisite.core.commands.ICommandRegistry;
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
    Logger getLogger();
    SimpleEventBus getEventBus();
    FileManager getFileManager();
    ConfigurationManager getConfigurationManager();
    ICommandRegistry getCommandRegistry();
    RequisiteClientSocket getRequisiteSocket();

    void openMenu();

    /* Utilities. */
    KeyBindRegistry getKeyBindRegistry();

    IEnhancedFontRenderer getEnhancedFontRenderer();
    IChatHelper getChatHelper();
    ColourHelper getColourHelper();
    LoggingHelper getLoggingHelper();
    UniversalLogger getUniversalLogger();
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