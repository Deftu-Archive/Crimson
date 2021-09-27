/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
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

package xyz.qalcyo.requisite.core;

import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import xyz.qalcyo.requisite.core.files.ConfigurationManager;
import xyz.qalcyo.requisite.core.integration.IMod;
import xyz.qalcyo.requisite.core.keybinds.KeyBind;
import xyz.qalcyo.requisite.core.keybinds.KeyBindRegistry;
import xyz.qalcyo.requisite.core.keybinds.KeyBindState;
import xyz.qalcyo.requisite.core.util.*;
import xyz.qalcyo.requisite.core.util.messages.IMessageQueue;
import xyz.qalcyo.requisite.core.commands.CommandRegistry;
import xyz.qalcyo.requisite.core.files.FileManager;
import xyz.qalcyo.requisite.core.files.configs.PrivacyConfigurations;
import xyz.qalcyo.requisite.core.hypixel.HypixelHelper;
import xyz.qalcyo.requisite.core.integration.IModIntegration;
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket;
import xyz.qalcyo.requisite.core.notifications.INotifications;
import xyz.qalcyo.requisite.core.rendering.IEnhancedFontRenderer;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.util.JsonApiHelper;
import xyz.qalcyo.simpleeventbus.SimpleEventBus;

import java.io.File;
import java.net.URI;
import java.util.Base64;

public interface IRequisite extends IMod {

    boolean initialize(File gameDir);
    default boolean finish(File gameDir) {
        boolean initial = initialize(gameDir);
        getConfigurationManager().addConfigurable(getPrivacyConfigurations());
        return initial;
    }

    default Logger getLogger() {
        return RequisiteDefaultImplementations.logger;
    }
    default SimpleEventBus getEventBus() {
        return RequisiteDefaultImplementations.eventBus;
    }
    FileManager getFileManager();
    ConfigurationManager getConfigurationManager();
    INotifications getNotifications();
    RequisiteClientSocket getRequisiteSocket();
    IModIntegration getModIntegration();
    CommandRegistry getCommandRegistry();
    KeyBindRegistry getKeyBindRegistry();
    RequisiteEventManager getInternalEventManager();
    IEventListener getInternalEventListener();

    default PrivacyConfigurations getPrivacyConfigurations() {
        return RequisiteDefaultImplementations.privacyConfigurations;
    }

    void openMenu();

    IEnhancedFontRenderer getEnhancedFontRenderer();
    IGuiHelper<?> getGuiHelper();
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
    IGlHelper getGlHelper();

    /* Default. */
    default URI fetchSocketUri() {
        if (Boolean.parseBoolean(System.getProperty("requisite.socket.debug", "false"))) {
            return URI.create("ws://localhost:8080/");
        }

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