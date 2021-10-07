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

package xyz.qalcyo.requisite.core.util.messages;

/**
 * Allows mods to send messages as the player without dealing with chat cooldowns.
 */
public interface IMessageQueue {
    /**
     * Queues a message to be sent as the player.
     *
     * @param message The message to send.
     * @param delay The delay to wait before sending the message.
     */
    void queue(String message, int delay);

    /**
     * Queues a message to be sent as the player.
     *
     * @param message The message to send.
     */
    void queue(String message);

    /**
     * Sends the latest entry in the message queue.
     *
     * @param entry The entry to send.
     */
    void run(MessageQueueEntry entry);
}