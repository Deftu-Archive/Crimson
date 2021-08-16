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

package xyz.matthewtgm.requisite.core.data;

import xyz.matthewtgm.json.entities.JsonObject;

public class HitBox {

    private float x, y, width, height;
    private int intX, intY, intWidth, intHeight;

    public HitBox(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.intX = (int) x;
        this.intY = (int) y;
        this.width = width;
        this.height = height;
        this.intWidth = (int) width;
        this.intHeight = (int) height;
    }

    public HitBox(JsonObject json) {
        this(json.get("x").getAsFloat(), json.get("y").getAsFloat(), json.get("width").getAsFloat(), json.get("height").getAsFloat());
    }

    public HitBox clone() {
        return new HitBox(x, y, width, height);
    }

    public boolean isInBounds(float x, float y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getIntX() {
        return intX;
    }

    public void setIntX(int intX) {
        this.intX = intX;
    }

    public int getIntY() {
        return intY;
    }

    public void setIntY(int intY) {
        this.intY = intY;
    }

    public int getIntWidth() {
        return intWidth;
    }

    public void setIntWidth(int intWidth) {
        this.intWidth = intWidth;
    }

    public int getIntHeight() {
        return intHeight;
    }

    public void setIntHeight(int intHeight) {
        this.intHeight = intHeight;
    }

}