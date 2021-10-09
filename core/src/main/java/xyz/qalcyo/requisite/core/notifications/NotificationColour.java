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

package xyz.qalcyo.requisite.core.notifications;

import xyz.qalcyo.requisite.core.RequisitePalette;
import xyz.qalcyo.requisite.core.data.ColourRGB;

public class NotificationColour {

    /* Constants. */
    private static final ColourRGB defaultBackground = new ColourRGB(0, 0, 0, 200);

    public static final NotificationColour DEFAULT = new NotificationColour(defaultBackground, RequisitePalette.getMain());
    public static final NotificationColour ALTERNATIVE = new NotificationColour(defaultBackground, new ColourRGB(255, 175, 0, 200));

    /* Data. */
    public final ColourRGB background;
    public final ColourRGB foreground;

    public NotificationColour(ColourRGB background, ColourRGB foreground) {
        this.background = background;
        this.foreground = foreground;
    }

    public static ColourRGB getDefaultBackground() {
        return defaultBackground;
    }

}