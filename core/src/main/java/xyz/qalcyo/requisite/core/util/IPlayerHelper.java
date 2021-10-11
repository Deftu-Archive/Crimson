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

package xyz.qalcyo.requisite.core.util;

import com.mojang.authlib.GameProfile;

import java.util.UUID;

public interface IPlayerHelper {
    /**
     * @return Whether the current player is present.
     */
    boolean isPlayerPresent();

    /**
     * @return The game profile of the current player.
     */
    GameProfile getGameProfile();

    /**
     * @return The display name of the current player.
     */
    String getUsername();

    /**
     * @return The UUID of the current player.
     */
    UUID getUuid();
}