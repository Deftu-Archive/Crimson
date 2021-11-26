/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core;

import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.Logger;
import xyz.qalcyo.crimson.core.compatibility.CrimsonTransmission;
import xyz.qalcyo.crimson.core.gui.screens.main.CrimsonMenuPage;
import xyz.qalcyo.crimson.core.gui.screens.main.impl.CrimsonControlsPage;
import xyz.qalcyo.crimson.core.networking.CrimsonClientSocket;
import xyz.qalcyo.eventbus.EventPriority;
import xyz.qalcyo.eventbus.QalcyoEventBus;
import xyz.qalcyo.eventbus.SubscribeEvent;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.mango.Strings;
import xyz.qalcyo.crimson.core.bridge.IBridge;
import xyz.qalcyo.crimson.core.configs.ConfigManager;
import xyz.qalcyo.crimson.core.events.initialization.InitializationEvent;
import xyz.qalcyo.crimson.core.integration.mods.IMod;
import xyz.qalcyo.crimson.core.keybinds.KeyBindRegistry;
import xyz.qalcyo.crimson.core.integration.mods.ModMetadata;
import xyz.qalcyo.crimson.core.util.*;
import xyz.qalcyo.crimson.core.util.messages.IMessageQueue;
import xyz.qalcyo.crimson.core.commands.CommandRegistry;
import xyz.qalcyo.crimson.core.files.FileManager;
import xyz.qalcyo.crimson.core.integration.hypixel.HypixelHelper;
import xyz.qalcyo.crimson.core.integration.mods.IModIntegration;
import xyz.qalcyo.crimson.core.notifications.INotifications;
import xyz.qalcyo.crimson.core.rendering.IEnhancedFontRenderer;
import xyz.qalcyo.json.util.JsonApiHelper;
import xyz.qalcyo.crimson.core.gui.factory.IComponentFactory;

import java.net.URI;

/**
 * The global parent to the Crimson class of the version you use.
 */
@SuppressWarnings({"unused"})
public interface CrimsonAPI extends IMod {

    /**
     * Initializes all of Crimson's core features for the version requested.
     *
     * @param event The game initialization event.
     */
    void initialize(InitializationEvent event);
    /**
     * Initializes Crimson, then completes it's own actions.
     *
     * @param event The game initialization event.
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    default void finish(InitializationEvent event) {
        initialize(event);
        getModIntegration().registerIntegratedMod(this);
    }

    /**
     * Called when the Crimson onboarding prompt is accepted.
     */
    void onOnboardingAccepted();
    /**
     * Called when the Crimson onboarding prompt is denied.
     */
    void onOnboardingDenied();

    /**
     * Provides an instance of Crimson's Log4J logger.
     *
     * @return Crimson's logger.
     */
    default Logger getLogger() {
        return CrimsonDefaultImplementations.LOGGER;
    }
    /**
     * Provides an instance of Crimson's JVM argument parser.
     *
     * @return Crimson's JVM argument parser.
     */
    default CrimsonJavaArguments getJavaArguments() {
        return CrimsonDefaultImplementations.JAVA_ARGUMENTS;
    }
    /**
     * Provides an instance of Crimson's inter-transmission API.
     *
     * @return Crimson's inter-transmission API.
     */
    default CrimsonTransmission getTransmission() {
        return CrimsonDefaultImplementations.TRANSMISSION;
    }
    /**
     * Provides an instance of Crimson's {@link QalcyoEventBus}.
     *
     * @return Crimson's event bus.
     */
    default QalcyoEventBus getEventBus() {
        return CrimsonDefaultImplementations.EVENT_BUS;
    }

    /**
     * Provides an instance of Crimson's file and directory manager.
     *
     * @return Crimson's file and directory manager.
     */
    FileManager getFileManager();
    /**
     * Provides an isntance of Crimson's own configuration manager.
     *
     * @return Crimson configuration manager.
     */
    ConfigManager getConfigManager();
    /**
     * Provides an instance of Crimson's notifications service.
     *
     * @return Crimson's notifications service.
     */
    INotifications getNotifications();
    /**
     * Provides an instance of Crimson's internal websocket.
     *
     * @return Crimson's internal websocket.
     */
    CrimsonClientSocket getCrimsonSocket();
    /**
     * Provides an instance of Crimson's mod integration API.
     *
     * @return Crimson's mod integration API.
     */
    IModIntegration getModIntegration();
    /**
     * Provides an instance of Crimson's custom command registry.
     *
     * @return Crimson's command registry.
     */
    CommandRegistry getCommandRegistry();
    /**
     * Provides an instance of Crimson's KeyBind API.
     *
     * @return Crimson's KeyBind API.
     */
    KeyBindRegistry getKeyBindRegistry();
    /**
     * Provides an instance of Crimson's custom GUI component factory.
     *
     * @return Crimson's custom GUI component factory.
     */
    IComponentFactory getComponentFactory();
    /**
     * Provides an instance of Crimson's "bridge", providing access to both internal and game methods globally.
     *
     * @return Crimson's bridge.
     */
    IBridge getBridge();
    /**
     * Provides an instance of Crimson's HTTP client.
     *
     * @return Crimson's HTTP client.
     */
    default OkHttpClient getHttpClient() {
        return CrimsonDefaultImplementations.HTTP_CLIENT;
    }
    /**
     * Provides an instance of Crimson's Pastebin factory.
     *
     * @return Crimson's Pastebin factory.
     */
    default PastebinFactory getPastebinFactory() {
        return CrimsonDefaultImplementations.PASTEBIN_FACTORY;
    }
    /**
     * Provides an instance of Crimson's internal event manager.
     *
     * @return Crimson's internal event manager.
     */
     InternalEventManager getInternalEventManager();
     /**
     * Provides an instance of Crimson's internal event listener.
     *
     * @return Crimson's internal event listener.
     */
    IEventListener getInternalEventListener();

    void openCrimsonMenu(Class<? extends CrimsonMenuPage> pageClass);
    /**
     * Opens Crimson's main menu, providing access to most Crimson configurations and services.
     *
     * @param pageIndex The index of the page to be opened initially.
     */
    void openCrimsonMenu(int pageIndex);
    /**
     * Opens Crimson's main menu, providing access to most Crimson configurations and services.
     */
    default void openCrimsonMenu() {
        openCrimsonMenu(CrimsonControlsPage.class);
    }
    /**
     * Opens Crimson's credits menu, providing access to all of Crimson's contributors and libraries.
     */
    void openCreditsMenu();
    /**
     * Opens Crimson's changelog menu.
     */
    void openChangelogMenu();

    /**
     * Provides an instance of Crimson's mod utility.
     *
     * @return Crimson's mod utility.
     */
    default ModHelper getModHelper() {
        return CrimsonDefaultImplementations.MOD_HELPER;
    }
    /**
     * Provides an instance of Crimson's enhanced font renderer utility, which gives you more options for rendering text to the screen.
     *
     * @return Crimson's enhanced font renderer utility.
     */
    IEnhancedFontRenderer getEnhancedFontRenderer();
    /**
     * Provides an instance of Crimson's GUI utility, allowing you to open GUIs easily.
     *
     * @return Crimson's GUI utility.
     */
    IGuiHelper<?> getGuiHelper();
    /**
     * Provides an instance of Crimson's chat utility, giving you easy access to the in-game chat.
     *
     * @return Crimson's chat utility.
     */
    IChatHelper getChatHelper();
    /**
     * Provides an instance of Crimson's support utility. This allows mods to access support features easily.
     *
     * @return Crimson's support utility.
     */
    ISupportHelper getSupportHelper();
    /**
     * Provides an instance of Crimson's colour utility, adding more colour to your mod.
     *
     * @return Crimson's colour utility.
     */
    default ColourHelper getColourHelper() {
        return CrimsonDefaultImplementations.COLOUR_HELPER;
    }
    /**
     * Provides an instance of Crimson's logging utility, debugging!
     *
     * @return Crimson's logging utility.
     */
    default LoggingHelper getLoggingHelper() {
        return CrimsonDefaultImplementations.LOGGING_HELPER;
    }
    /**
     * Provides an instance of Crimson's universal logger, allowing you to create a logger specifically for the class it's used in.
     *
     * @return Crimson's universal logger.
     */
    default UniversalLogger getUniversalLogger() {
        return CrimsonDefaultImplementations.UNIVERSAL_LOGGER;
    }
    /**
     * Provides an instance of Crimson's clipboard utility, giving you access to the contents of the user's clipboard.
     *
     * @return Crimson's clipboard utility.
     */
    default ClipboardHelper getClipboardHelper() {
        return CrimsonDefaultImplementations.CLIPBOARD_HELPER;
    }
    /**
     * Provides an instance of Crimson's date utility, letting you check for special events.
     *
     * @return Crimson's date helper.
     */
    default DateHelper getDateHelper() {
        return CrimsonDefaultImplementations.DATE_HELPER;
    }
    /**
     * Provides an instance of Crimson's easing utility.
     *
     * @return Crimson's easing utility.
     */
    default EasingHelper getEasingHelper() {
        return CrimsonDefaultImplementations.EASING_HELPER;
    }
    /**
     * Provides an instance of Crimson's math utility, making your calculations easier.
     *
     * @return Crimson's math utility.
     */
    default MathHelper getMathHelper() {
        return CrimsonDefaultImplementations.MATH_HELPER;
    }
    /**
     * Provides an instance of Crimson's mouse utility, allowing you to interact with the mouse easily.
     *
     * @return Crimson's mouse utility.
     */
    IMouseHelper getMouseHelper();
    /**
     * Provides an instance of Crimson's reflection utility. This makes it easier to interact with Java's reflection API.
     *
     * @return Crimson's reflection utility.
     */
    default ReflectionHelper getReflectionHelper() {
        return CrimsonDefaultImplementations.REFLECTION_HELPER;
    }
    /**
     * Provides an instance of Crimson's position helper.
     *
     * @return Crimson's position helper.
     */
    IPositionHelper getPositionHelper();
    /**
     * Provides an instance of Crimson's roman numeral cache.
     *
     * @return Crimson's roman numeral cache.
     */
    default RomanNumeral getRomanNumerals() {
        return CrimsonDefaultImplementations.ROMAN_NUMERAL;
    }
    /**
     * Provides an instance of Crimson's Hypixel server utility.
     *
     * @return Crimson's Hypixel utility.
     */
    default HypixelHelper getHypixelHelper() {
        return CrimsonDefaultImplementations.HYPIXEL_HELPER;
    }
    /**
     * Provides an instance of Crimson's rendering utility.
     *
     * @return Crimson's rendering helper.
     */
    IRenderHelper getRenderHelper();
    /**
     * Provides an instance of Crimson's string utility.
     *
     * @return Crimson's string utility.
     */
    default StringHelper getStringHelper() {
        return CrimsonDefaultImplementations.STRING_HELPER;
    }
    /**
     * Provides an instance of Crimson's in-game chat message queue. This sends messages as the player.
     *
     * @return Crimson's in-game chat message queue.
     */
    IMessageQueue getMessageQueue();
    /**
     * Provides an instance of Crimson's server utility.
     *
     * @return Crimson's server utility.
     */
    IServerHelper getServerHelper();
    /**
     * Provides an instance of Crimson's OpenGL utility.
     *
     * @return Crimson's OpenGL utility.
     */
    IGlHelper getGlHelper();
    /**
     * Provides an instance of Crimson's resource utility.
     *
     * @return Crimson's resource utility.
     */
    IResourceHelper getResourceHelper();
    /**
     * Provides an instance of Crimson's keyboard utility.
     *
     * @return Crimson's keyboard utility.
     */
    IKeyboardHelper getKeyboardHelper();

    /**
     * Fetches and provides Crimson's websocket URI.
     *
     * @return Crimson's websocket URI.
     */
    default URI retrieveSocketUri() {
        String uri = getJavaArguments().getSocketUri();
        if (uri == null) {
            try {
                JsonObject object =JsonApiHelper.getJsonObject(getJavaArguments().getMetaUrl());
                if (object != null) {
                    uri = object.getAsString("socket");
                } else {
                    uri = "ws://localhost:8080";
                }
            } catch (Exception ignored) {
            }
        }
        uri = uri.replace("{}", "v" + CrimsonConstants.SOCKET_VERSION);
        return URI.create(uri);
    }

    /**
     * @param suffix The suffix to add after the Crimson text.
     * @return Crimson's prefix for chat messages.
     */
    default String getChatPrefix(String suffix) {
        return ChatColour.GRAY + ChatColour.BOLD.toString() + "[" + getJavaArguments().getChatPrefixColour() + name() + (Strings.isNullOrEmpty(suffix) ? "" : " " + suffix) + ChatColour.RESET + ChatColour.GRAY + ChatColour.BOLD + "]";
    }

    /**
     * @return Crimson's prefix for chat messages.
     */
    default String getChatPrefix() {
        return getChatPrefix(null);
    }

    /**
     * @return Crimson's name.
     */
    default String name() {
        return CrimsonInfo.NAME;
    }
    /**
     * @return Crimson's current version.
     */
    default String version() {
        return CrimsonInfo.VER;
    }
    /**
     * @return Crimson's ID for mod loader purposes.
     */
    default String id() {
        return CrimsonInfo.ID;
    }

    /**
     * Provides Crimson's mod integration API it's own metadata.
     *
     * @return Crimson's own metadata.
     */
    default ModMetadata getMetadata() {
        return ModMetadata.from(name(), version())
                .setCommand("/crimson");
    }

    /**
     * Retrieves a cached instance of Crimson.
     *
     * @return An instance of Crimson.
     */
    static CrimsonAPI retrieveInstance() {
        try {
            CrimsonAPI cached = CrimsonConstants.getInstance();
            if (cached == null) {
                return CrimsonConstants.INSTANCE = (CrimsonAPI) Class.forName("xyz.qalcyo.crimson.Crimson").getDeclaredMethod("getInstance").invoke(null);
            }

            return cached;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}