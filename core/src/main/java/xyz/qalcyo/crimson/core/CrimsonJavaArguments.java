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

import xyz.qalcyo.crimson.core.util.ChatColour;

import java.util.regex.Pattern;

/**
 * Adds easy access to Crimson's JVM arguments.
 */
public class CrimsonJavaArguments {

    private static final Pattern booleanPattern = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);

    private final boolean socketDebug = retrieveSocketDebug();
    private final String socketUri = System.getProperty("crimson.socket.uri", null);
    private final String metaUrl = System.getProperty("crimson.meta.url", "https://raw.githubusercontent.com/Qalcyo/DataStorage/master/crimson/meta.json");

    private final ChatColour chatPrefixColour = retrieveChatPrefixColour();

    /**
     * @return Whether the Crimson socket is in debug mode.
     */
    public boolean isSocketDebug() {
        return socketDebug;
    }

    /**
     * @return Crimson's Socket URI.
     */
    public String getSocketUri() {
        return socketUri;
    }

    /**
     * @return Crimson's Socket URL.
     */
    public String getMetaUrl() {
        return metaUrl;
    }

    /**
     * @return Crimson's chat prefix colour, provided as a ChatColour instance.
     */
    public ChatColour getChatPrefixColour() {
        return chatPrefixColour;
    }

    /**
     * @return Crimson's socket debug state.
     */
    private static boolean retrieveSocketDebug() {
        String property = System.getProperty("crimson.socket.debug", "false");
        if (!booleanPattern.matcher(property).matches())
            throw new IllegalArgumentException("JVM property 'crimson.socket.debug' must be a boolean!");
        return Boolean.parseBoolean(property);
    }

    /**
     * @return Crimson's chat prefix colour.
     */
    private static ChatColour retrieveChatPrefixColour() {
        String property = System.getProperty("crimson.chat.prefix", "GOLD");
        if (!ChatColour.isPresent(property))
            throw new IllegalArgumentException("JVM property 'crimson.chat.prefix' must be a ChatColour!");
        return ChatColour.fromInput(property);
    }

}