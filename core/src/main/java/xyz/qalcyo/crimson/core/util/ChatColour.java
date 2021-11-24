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

package xyz.qalcyo.crimson.core.util;

public enum ChatColour {

    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    OBFUSCATED('k', true),
    BOLD('l', true),
    STRIKETHROUGH('m', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    RESET('r');

    public static final String COLOR_CHAR = "\u00a7";
    private final char code;
    private final boolean isFormat;
    private final String toString;

    ChatColour(char code) {
        this(code, false);
    }

    ChatColour(char code, boolean isFormat) {
        this.code = code;
        this.isFormat = isFormat;
        this.toString = new String(new char[] {COLOR_CHAR.charAt(0), code});
    }

    public char getCharacter() {
        return code;
    }

    /**
     * @return  the String value of the ChatColour.
     */
    public String toString() {
        return toString;
    }

    /**
     * @return Whether the ChatColour represents a format change.
     */
    public boolean isFormat() {
        return isFormat;
    }

    /**
     * @return Whether the ChatColour represents a colour change.
     */
    public boolean isColor() {
        return !isFormat && this != ChatColour.RESET;
    }

    /**
     * @return A translated value of the input based on {@code altColorChar}.
     */
    public static String translateAlternateColorCodes(char altColorChar, String input) {
        final char[] b = input.toCharArray();
        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_CHAR.charAt(0);
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    /**
     * @return A ChatColour instance from the String provided.
     */
    public static ChatColour fromInput(String input) {
        for (ChatColour value : values()) {
            if (value.name().equalsIgnoreCase(input)) {
                return value;
            }
        }

        return null;
    }

    /**
     * @return Whether the ChatColour is present in the String provided or not.
     */
    public static boolean isPresent(String input) {
        return fromInput(input) != null;
    }

}