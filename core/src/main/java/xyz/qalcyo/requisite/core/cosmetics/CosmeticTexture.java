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

package xyz.qalcyo.requisite.core.cosmetics;

import xyz.qalcyo.json.entities.JsonObject;

import java.io.InputStream;

public class CosmeticTexture {
    public final String name;
    public final InputStream texture;

    public CosmeticTexture(String name, InputStream texture) {
        this.name = name;
        this.texture = texture;
    }

    public String toString() {
        return new JsonObject()
                .add("name", name)
                .add("texture", texture)
                .getAsString();
    }

}