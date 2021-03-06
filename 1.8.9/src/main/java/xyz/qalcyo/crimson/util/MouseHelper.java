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

package xyz.qalcyo.crimson.util;

import gg.essential.universal.UResolution;
import org.lwjgl.input.Mouse;
import xyz.qalcyo.crimson.core.util.IMouseHelper;

public class MouseHelper implements IMouseHelper {

    public double getMouseX() {
        return Mouse.getX() * UResolution.getScaledWidth() / UResolution.getWindowWidth();
    }

    public double getMouseY() {
        return UResolution.getScaledHeight() - Mouse.getY() * UResolution.getScaledHeight() / UResolution.getWindowHeight() - 1;
    }

    public boolean isMouseButtonDown() {
        return Mouse.getEventButtonState();
    }

}