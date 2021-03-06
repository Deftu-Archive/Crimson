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
import java.util.regex.Pattern;

public class StringHelper {

    private final Pattern formattingCodePattern = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    /**
     * Removes formatting codes from a string and returns it.
     * @return The string provided without formatting codes.
     */
    public String removeFormattingCodes(String input) {
        return formattingCodePattern.matcher(input).replaceAll("");
    }

    /**
     * @return The pattern for formatting codes.
     */
    public Pattern getFormattingCodePattern() {
        return formattingCodePattern;
    }

}