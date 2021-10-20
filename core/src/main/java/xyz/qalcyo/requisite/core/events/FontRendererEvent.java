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

package xyz.qalcyo.requisite.core.events;

import xyz.qalcyo.eventbus.Event;

public class FontRendererEvent extends Event {
    public String string;
    public FontRendererEvent(String string) {
        this.string = string;
    }

    public static class RenderStringEvent extends FontRendererEvent {
        public float x, y;
        public int colour;
        public boolean dropShadow;
        public RenderStringEvent(String string, float x, float y, int colour, boolean dropShadow) {
            super(string);
            this.x = x;
            this.y = y;
            this.colour = colour;
            this.dropShadow = dropShadow;
        }
    }

    public static class WidthGottenEvent extends FontRendererEvent {
        public WidthGottenEvent(String string) {
            super(string);
        }
    }

}