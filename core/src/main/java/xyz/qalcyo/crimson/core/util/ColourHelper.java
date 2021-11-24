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

import xyz.qalcyo.crimson.core.data.ColourRGB;

import java.awt.*;

public class ColourHelper {

    /**
     * @return A changing colour based on the users' computer time. Simulates a "chroma" colour.
     */
    public int timeBasedChroma() {
        long l = System.currentTimeMillis();
        return Color.HSBtoRGB(l % 2000L / 2000.0f, 1.0f, 1.0f);
    }

    /**
     * @return A ColourRGB instance based on the users' computer time and the positions provided, which when used multiple times can simulate a "chroma" colour.
     */
    public ColourRGB getChroma(double x, double y) {
        float v = 2000.0f;
        return new ColourRGB(Color.HSBtoRGB((float)((System.currentTimeMillis() - x * 10.0 * 1.0 - y * 10.0 * 1.0) % v) / v, 1.0f, 1.0f));
    }

    /**
     * @return An integer representing the alpha value of the colour's RGB value given.
     */
    public int getAlpha(int colour) {
        return (colour >> 24 & 255);
    }

}
