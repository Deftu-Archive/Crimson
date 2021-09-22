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

package xyz.qalcyo.requisite.core.networking.packets.cosmetics;

import xyz.qalcyo.json.entities.JsonArray;
import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData;
import xyz.qalcyo.requisite.core.cosmetics.PlayerCosmeticData;
import xyz.qalcyo.requisite.core.networking.BasePacket;
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket;

import java.util.List;

public class CosmeticRetrievePacket extends BasePacket {

    private final String uuid;

    public CosmeticRetrievePacket(String uuid) {
        super("COSMETIC_RETRIEVE");
        this.uuid = uuid;
    }

    public CosmeticRetrievePacket() {
        this(null);
    }

    public void send(RequisiteClientSocket socket, JsonObject data) {
        data.add("uuid", uuid);
    }

    public void receive(RequisiteClientSocket socket, JsonObject packet, JsonObject data) {
        if (data.hasKey("uuid") && data.hasKey("owned") && data.hasKey("enabled")) {
            JsonElement ownedElement = data.get("owned");
            JsonElement enabledElement = data.get("enabled");
            if (ownedElement.isJsonArray() && enabledElement.isJsonArray()) {
                JsonArray owned = ownedElement.getAsJsonArray();
                JsonArray enabled = enabledElement.getAsJsonArray();

                List<CosmeticData> ownedCosmetics = Lists.newArrayList();
                List<CosmeticData> enabledCosmetics = Lists.newArrayList();

                for (JsonElement cosmetic : owned) {
                    CosmeticData retrieved = socket.getRequisite().getCosmeticManager().fromId(cosmetic.getAsString());
                    if (retrieved != null) {
                        ownedCosmetics.add(retrieved);
                    }
                }

                for (JsonElement cosmetic : enabled) {
                    CosmeticData retrieved = socket.getRequisite().getCosmeticManager().fromId(cosmetic.getAsString());
                    if (retrieved != null) {
                        enabledCosmetics.add(retrieved);
                    }
                }

                socket.getRequisite().getCosmeticManager().getPlayerData().put(data.getAsString("uuid"), new PlayerCosmeticData(ownedCosmetics, enabledCosmetics));
            }
        }
    }

}