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

package xyz.qalcyo.crimson.core.util;

public interface IServerHelper {
    /**
     * Joins the server with the IP address provided and the port provided.
     */
    void join(String ip, int port);
    /**
     * Joins the server with the IP address provided.
     */
    void join(String ip);

    /**
     * @return The server brand of the current server.
     */
    String getBrand();

    /**
     * @return Whether the player is in singleplayer.
     */
    boolean isSingleplayer();
}