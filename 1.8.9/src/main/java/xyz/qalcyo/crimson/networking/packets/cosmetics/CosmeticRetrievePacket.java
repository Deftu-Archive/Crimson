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

package xyz.qalcyo.crimson.networking.packets.cosmetics;

import xyz.qalcyo.crimson.core.networking.CrimsonClientSocket;
import xyz.qalcyo.json.entities.JsonArray;
import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.crimson.Crimson;
import xyz.qalcyo.crimson.core.networking.BasePacket;
import xyz.qalcyo.crimson.cosmetics.BaseCosmetic;
import xyz.qalcyo.crimson.cosmetics.PlayerCosmeticHolder;

import java.util.List;

public class CosmeticRetrievePacket extends BasePacket {

    private final String uuid;

    public CosmeticRetrievePacket(String uuid) {
        super("COSMETIC_RETRIEVE", false);
        this.uuid = uuid;
    }

    public CosmeticRetrievePacket() {
        this(null);
    }

    public void send(CrimsonClientSocket socket, JsonObject data) {
        data.add("uuid", uuid);
    }

    public void receive(CrimsonClientSocket socket, JsonObject packet, JsonObject data) {
        if (data.hasKey("uuid") && data.hasKey("owned") && data.hasKey("enabled")) {
            JsonElement ownedElement = data.get("owned");
            JsonElement enabledElement = data.get("enabled");
            if (ownedElement.isJsonArray() && enabledElement.isJsonArray()) {
                JsonArray ownedArray = ownedElement.getAsJsonArray();
                JsonArray enabledArray = enabledElement.getAsJsonArray();

                List<BaseCosmetic> owned = processCosmeticArray(ownedArray);
                List<BaseCosmetic> enabled = processCosmeticArray(enabledArray);

                String uuid = data.getAsString("uuid");
                Crimson.getInstance().getCosmeticManager().getPlayerData().put(uuid, new PlayerCosmeticHolder(uuid, owned, enabled));
            }
        }
    }

    private List<BaseCosmetic> processCosmeticArray(JsonArray array) {
        List<BaseCosmetic> value = Lists.newArrayList();

        for (JsonElement element : array) {
            if (element.isString()) {
                value.add(Crimson.getInstance().getCosmeticManager().fromId(element.getAsString()));
            }
        }

        value.removeIf(cosmetic -> cosmetic == null);
        return value;
    }

}