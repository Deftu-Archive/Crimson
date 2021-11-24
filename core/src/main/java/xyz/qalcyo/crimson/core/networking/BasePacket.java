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

package xyz.qalcyo.crimson.core.networking;

import xyz.qalcyo.json.entities.JsonObject;

public abstract class BasePacket {

    private final String type;
    private final JsonObject data;
    private final boolean mass;

    public BasePacket(String type, boolean mass) {
        this.type = type;
        this.data = new JsonObject();
        this.mass = mass;
    }

    public BasePacket(String type) {
        this(type, false);
    }

    public abstract void send(CrimsonClientSocket socket, JsonObject data);
    public abstract void receive(CrimsonClientSocket socket, JsonObject packet, JsonObject data);

    final JsonObject getData() {
        return data;
    }

    public final String getType() {
        return type;
    }

    public final boolean isMass() {
        return mass;
    }

    public final JsonObject jsonify() {
        JsonObject value = new JsonObject();
        value.add("type", type);
        value.add("data", data);
        return value;
    }

}