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

package xyz.qalcyo.crimson.core.events;

import xyz.qalcyo.eventbus.Cancellable;
import xyz.qalcyo.eventbus.Event;

@Cancellable
public class ChatMessageReceivedEvent extends Event {
    public final String message;
    public final ChatMessageType type;
    public ChatMessageReceivedEvent(String message, byte type) {
        this.message = message;
        this.type = ChatMessageType.fromType(type);
    }
    public enum ChatMessageType {
        STANDARD((byte) 0),
        SYSTEM((byte) 1);

        private final byte type;
        ChatMessageType(byte type) {
            this.type = type;
        }

        public static ChatMessageType fromType(byte type) {
            for (ChatMessageType value : values()) if (value.type == type) return value;
            return null;
        }

        public byte getType() {
            return type;
        }
    }
}