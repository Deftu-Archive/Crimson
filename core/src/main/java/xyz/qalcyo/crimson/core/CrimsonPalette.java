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

import xyz.qalcyo.crimson.core.data.ColourRGB;

/**
 * Provides mods and internal Crimson menus access to it's colour palette.
 */
public class CrimsonPalette {

    private static final ColourRGB primary = new ColourRGB(40, 157, 140);
    private static final ColourRGB secondary = new ColourRGB(28, 28, 28);
    private static final ColourRGB tertiary = new ColourRGB(44, 45, 50);

    private static final ColourRGB success = new ColourRGB(43, 181, 66);
    private static final ColourRGB fail = new ColourRGB(181, 43, 43);

    /**
     * Provides a ColourRGB instance of Qalcyo's theme colour.
     *
     * @return Qalcyo's primary colour
     */
    public static ColourRGB getPrimary() {
        return primary;
    }

    /**
     * Provides a ColourRGB instance of Qalcyo's background colour.
     *
     * @return Qalcyo's secondary colour
     */
    public static ColourRGB getSecondary() {
        return secondary;
    }

    /**
     * Provides a ColourRGB instance of Qalcyo's tertiary colour.
     *
     * @return Qalcyo's tertiary colour.
     */
    public static ColourRGB getTertiary() {
        return tertiary;
    }

    /**
     * Provides a ColourRGB instance of the colour Qalcyo uses to indicate success.
     *
     * @return Qalcyo's "success" colour
     */
    public static ColourRGB getSuccess() {
        return success;
    }

    /**
     * Provides a ColourRGB instance of the colour Qalcyo uses to indicate failure.
     *
     * @return Qalcyo's "failure" colour
     */
    public static ColourRGB getFail() {
        return fail;
    }

}