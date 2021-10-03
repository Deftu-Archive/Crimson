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

import xyz.qalcyo.requisite.core.data.ColourRGB;

public class RequisitePalette {

    private static final ColourRGB main = new ColourRGB(40, 157, 140);

    private static final ColourRGB componentContent = new ColourRGB(28, 28, 28, 189);


    private static final ColourRGB success = new ColourRGB(43, 181, 66);
    private static final ColourRGB fail = new ColourRGB(181, 43, 43);

    public static ColourRGB getMain() {
        return main;
    }

    public static ColourRGB getComponentContent() {
        return componentContent;
    }

    public static ColourRGB getSuccess() {
        return success;
    }

    public static ColourRGB getFail() {
        return fail;
    }

}