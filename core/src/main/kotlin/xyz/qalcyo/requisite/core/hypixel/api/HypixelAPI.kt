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

package xyz.qalcyo.requisite.core.hypixel.api

import xyz.qalcyo.json.entities.JsonObject
import xyz.qalcyo.json.util.JsonApiHelper

class HypixelAPI {

    val baseUrl = "https://api.hypixel.net/"

    fun isValidKey(key: String): Boolean {
        val gotten = JsonApiHelper.getJsonObject(baseUrl + "key?key=" + key)
        return gotten.hasKey("success") && gotten.getAsBoolean("success")
    }

    fun getPlayer(key: String, uuid: String): JsonObject =
        JsonApiHelper.getJsonObject(String.format(baseUrl + "player?uuid=%s&key=%s", uuid, key))
    fun getStatus(key: String, uuid: String): JsonObject =
        JsonApiHelper.getJsonObject(String.format(baseUrl + "status?uuid=%s&key=%s", uuid, key))
    fun getGuild(key: String, uuid: String): JsonObject =
        JsonApiHelper.getJsonObject(String.format(baseUrl + "guild?player=%s&key=%s", uuid, key))

}