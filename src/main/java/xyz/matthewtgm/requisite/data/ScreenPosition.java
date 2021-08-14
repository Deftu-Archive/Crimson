/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.matthewtgm.requisite.data;

/**
 * Adapted from EvergreenHUD under LGPL 3.0 license
 * https://github.com/isXander/EvergreenHUD/blob/kotlin-rewrite/LICENSE.md
 *
 * @author isXander
 */
public class ScreenPosition {

    private float x, y;

    private ScreenPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return ScreenHelper.getScaledWidth() * x;
    }

    public ScreenPosition setX(float x) {
        this.x = MathHelper.percentageOf(x, 0, ScreenHelper.getScaledWidth());
        return this;
    }

    public float getY() {
        return ScreenHelper.getScaledHeight() * y;
    }

    public ScreenPosition setY(float y) {
        this.y = MathHelper.percentageOf(y, 0, ScreenHelper.getScaledHeight());
        return this;
    }

    public ScreenPosition setPosition(float x, float y) {
        setX(x);
        setY(y);
        return this;
    }

    public static ScreenPosition fromRaw(float x, float y) {
        return new ScreenPosition(MathHelper.percentageOf(x, 0, ScreenHelper.getScaledWidth()), MathHelper.percentageOf(y, 0, ScreenHelper.getScaledHeight()));
    }

    public static ScreenPosition fromScaled(float x, float y) {
        return new ScreenPosition(x, y);
    }

}