/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.qalcyo.requisite.core.hypixel.api;

import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.util.JsonApiHelper;

public class HypixelAPI {

    private final String baseUrl = "https://api.hypixel.net/";

    public boolean isValidKey(String key) {
        JsonObject gotten = JsonApiHelper.getJsonObject(baseUrl + "key?key=" + key);
        return gotten.hasKey("success") && gotten.getAsBoolean("success");
    }

    public JsonObject getPlayer(String key, String uuid) {
        return JsonApiHelper.getJsonObject(String.format(baseUrl + "player?uuid=%s&key=%s", uuid, key));
    }

    public JsonObject getStatus(String key, String uuid) {
        return JsonApiHelper.getJsonObject(String.format(baseUrl + "status?uuid=%s&key=%s", uuid, key));
    }

    public JsonObject getGuild(String key, String uuid) {
        return JsonApiHelper.getJsonObject(String.format(baseUrl + "guild?player=%s&key=%s", uuid, key));
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}