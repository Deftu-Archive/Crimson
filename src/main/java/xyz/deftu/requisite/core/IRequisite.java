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

package xyz.deftu.requisite.core;

import org.apache.logging.log4j.Logger;
import xyz.deftu.requisite.core.cosmetics.CosmeticManager;
import xyz.deftu.requisite.core.discord.DiscordCore;
import xyz.deftu.requisite.core.files.ConfigurationManager;
import xyz.deftu.requisite.core.files.FileManager;
import xyz.deftu.requisite.core.hud.HudRegistry;
import xyz.deftu.requisite.core.hypixel.HypixelHelper;
import xyz.deftu.requisite.core.integration.IModIntegration;
import xyz.deftu.requisite.core.keybinds.KeyBindRegistry;
import xyz.deftu.requisite.core.networking.RequisiteClientSocket;
import xyz.deftu.requisite.core.notifications.INotifications;
import xyz.deftu.requisite.core.rendering.IEnhancedFontRenderer;
import xyz.deftu.requisite.core.util.*;
import xyz.deftu.requisite.core.util.messages.IMessageQueue;
import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.json.util.JsonApiHelper;
import xyz.deftu.simpleeventbus.SimpleEventBus;

import java.io.File;
import java.net.URI;
import java.util.Base64;

public interface IRequisite {

    void initialize(File gameDir);

    default Logger getLogger() {
        return RequisiteDefaultImplementations.logger;
    }
    default SimpleEventBus getEventBus() {
        return RequisiteDefaultImplementations.eventBus;
    }
    FileManager getFileManager();
    ConfigurationManager getConfigurationManager();
    CosmeticManager getCosmeticManager();
    IModIntegration getModIntegration();
    RequisiteEventManager getInternalEventManager();
    IEventListener getInternalEventListener();
    RequisiteClientSocket getRequisiteSocket();

    void openMenu();

    KeyBindRegistry getKeyBindRegistry();
    HudRegistry getHudRegistry();

    IEnhancedFontRenderer getEnhancedFontRenderer();
    IPlayerHelper getPlayerHelper();
    IChatHelper getChatHelper();
    default ColourHelper getColourHelper() {
        return RequisiteDefaultImplementations.colourHelper;
    }
    default LoggingHelper getLoggingHelper() {
        return RequisiteDefaultImplementations.loggingHelper;
    }
    UniversalLogger getUniversalLogger();
    default ClipboardHelper getClipboardHelper() {
        return RequisiteDefaultImplementations.clipboardHelper;
    }
    default DateHelper getDateHelper() {
        return RequisiteDefaultImplementations.dateHelper;
    }
    default EasingHelper getEasingHelper() {
        return RequisiteDefaultImplementations.easingHelper;
    }
    default MathHelper getMathHelper() {
        return RequisiteDefaultImplementations.mathHelper;
    }
    IMouseHelper getMouseHelper();
    default Multithreading getMultithreading() {
        return RequisiteDefaultImplementations.multithreading;
    }
    INotifications getNotifications();
    default ReflectionHelper getReflectionHelper() {
        return RequisiteDefaultImplementations.reflectionHelper;
    }
    IPositionHelper getPositionHelper();
    default RomanNumeral getRomanNumerals() {
        return RequisiteDefaultImplementations.romanNumerals;
    }
    HypixelHelper getHypixelHelper();
    IRenderHelper getRenderHelper();
    default StringHelper getStringHelper() {
        return RequisiteDefaultImplementations.stringHelper;
    }
    IMessageQueue getMessageQueue();
    IServerHelper getServerHelper();
    default MojangAPI getMojangApi() {
        return RequisiteDefaultImplementations.mojangApi;
    }
    default DiscordCore getDiscordCore() {
        return RequisiteDefaultImplementations.discordCore;
    }

    /* Default. */
    default URI fetchSocketUri() {
        JsonObject object = JsonApiHelper.getJsonObject("https://raw.githubusercontent.com/TGMDevelopment/RequisiteData/main/websocket.json", true);
        String encoded = object.getAsString("uri");
        for (int i = 0; i < object.getAsInt("loop"); i++)
            encoded = new String(Base64.getDecoder().decode(encoded));
        return URI.create(encoded);
    }

    default String getChatPrefix() {
        return ChatColour.GRAY + "[" + ChatColour.GOLD + name() + ChatColour.GRAY + "]";
    }
    default String name() {
        return RequisiteInfo.NAME;
    }
    default String version() {
        return RequisiteInfo.VER;
    }
    default String id() {
        return RequisiteInfo.ID;
    }

}