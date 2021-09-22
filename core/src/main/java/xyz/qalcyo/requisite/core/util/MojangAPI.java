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

import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.util.JsonApiHelper;

public class MojangAPI {

    /**
     * @param username The username of the player to fetch
     * @return The UUID of the player.
     * @author Unknown
     */
    public String fetchUuid(String username) {
        JsonObject response = JsonApiHelper.getJsonObject("https://api.mojang.com/users/profiles/minecraft/" + username);
        if (response == null)
            return null;
        return response.getAsString("id");
    }

}