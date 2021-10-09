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

package xyz.qalcyo.requisite.core.networking;

import xyz.qalcyo.json.entities.JsonObject;

public enum WebSocketClose {

    UNKNOWN(0),

    NORMAL(1000),
    GOING_AWAY(1001),
    PROTOCOL_ERROR(1002),
    UNSUPPORTED(1003),
    NO_STATUS(1005),
    ABNORMAL(1006),
    UNSUPPORTED_PAYLOAD(1007),
    POLICY_VIOLATION(1008),
    TOO_LARGE(1009),
    MANDATORY_EXTENSION(1010),
    SERVER_ERROR(1011),
    SERVICE_RESTART(1012),
    TRY_AGAIN_LATER(1013),
    BAD_GATEWAY(1014),
    TLS_HANDSHAKE_FAIL(1015);

    private final int code;
    WebSocketClose(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return new JsonObject().add("name", name()).add("code", code).getAsString();
    }

    public static WebSocketClose fromCode(int code) {
        for (WebSocketClose value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }

        return UNKNOWN;
    }

}