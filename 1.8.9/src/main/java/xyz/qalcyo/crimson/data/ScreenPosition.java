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

package xyz.qalcyo.crimson.data;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import xyz.qalcyo.crimson.Crimson;
import xyz.qalcyo.crimson.core.data.IScreenPosition;

public class ScreenPosition implements IScreenPosition {

    private float x, y;

    private ScreenPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        return resolution.getScaledWidth() * x;
    }

    public ScreenPosition setX(float x) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        this.x = Crimson.getInstance().getMathHelper().percentageOf(x, 0, resolution.getScaledWidth());
        return this;
    }

    public float getY() {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        return resolution.getScaledHeight() * y;
    }

    public ScreenPosition setY(float y) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        this.y = Crimson.getInstance().getMathHelper().percentageOf(0, y, resolution.getScaledHeight());
        return this;
    }

    public IScreenPosition setPosition(float x, float y) {
        setX(x);
        setY(y);
        return this;
    }

    public String toString() {
        return toJson().getAsString();
    }

    public static ScreenPosition fromRaw(float x, float y) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        return new ScreenPosition(Crimson.getInstance().getMathHelper().percentageOf(x, 0, resolution.getScaledWidth()), Crimson.getInstance().getMathHelper().percentageOf(y, 0, resolution.getScaledHeight()));
    }

    public static ScreenPosition fromScaled(float x, float y) {
        return new ScreenPosition(x, y);
    }

}