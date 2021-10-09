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

package xyz.qalcyo.requisite.core.networking.packets;

import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.requisite.core.networking.BasePacket;
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket;
import xyz.qalcyo.requisite.core.networking.WebSocketClose;

import java.util.UUID;

public class GreetingPacket extends BasePacket {

    public GreetingPacket() {
        super("GREETING");
    }

    public void send(RequisiteClientSocket socket, JsonObject data) {
    }

    public void receive(RequisiteClientSocket socket, JsonObject packet, JsonObject data) {
        System.out.println(data);
        if (!data.hasKey("id")) {
            socket.close(WebSocketClose.PROTOCOL_ERROR);
        } else {
            socket.updateSessionId(UUID.fromString(data.getAsString("id")));
        }
    }

}