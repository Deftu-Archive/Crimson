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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CosmeticData {

    private final String name, id;
    private final CosmeticType type;
    private final CosmeticTexture texture;
    private final List<CosmeticFlag> flags;

    CosmeticData(String name, String id, CosmeticType type, CosmeticTexture texture, CosmeticFlag... flags) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.texture = texture;

        this.flags = new LinkedList<>();
        this.flags.addAll(Arrays.asList(flags));
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isId(String id) {
        return this.id != null && this.id.equals(id);
    }

    public CosmeticType getType() {
        return type;
    }

    public boolean isType(CosmeticType type) {
        return this.type == type;
    }

    public CosmeticTexture getTexture() {
        return texture;
    }

    public List<CosmeticFlag> getFlags() {
        return flags;
    }

    public boolean hasFlag(CosmeticFlag flag) {
        return this.flags.contains(flag);
    }

    public String toString() {
        return new JsonObject()
                .add("name", name)
                .add("id", id)
                .add("type", type)
                .add("texture", texture)
                .add("flags", flags)
                .getAsString();
    }

    public static CosmeticData from(String name, String id, CosmeticType type, CosmeticTexture texture, CosmeticFlag... flags) {
        return new CosmeticData(name, id, type, texture, flags);
    }

    public static CosmeticData from(String name, CosmeticType type, CosmeticTexture texture, CosmeticFlag... flags) {
        return from(name, name.toUpperCase().replace(' ', '_'), type, texture, flags);
    }

}