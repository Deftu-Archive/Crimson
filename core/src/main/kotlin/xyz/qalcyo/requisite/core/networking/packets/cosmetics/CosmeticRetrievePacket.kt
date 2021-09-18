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

package xyz.qalcyo.requisite.core.networking.packets.cosmetics

import xyz.qalcyo.json.entities.JsonObject
import xyz.qalcyo.mango.Lists
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData
import xyz.qalcyo.requisite.core.cosmetics.PlayerCosmeticData
import xyz.qalcyo.requisite.core.networking.BasePacket
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket

class CosmeticRetrievePacket @JvmOverloads constructor(private val uuid: String? = null) : BasePacket("COSMETIC_RETRIEVE") {

    override fun send(socket: RequisiteClientSocket, data: JsonObject) {
        data.add("uuid", uuid)
    }

    override fun receive(socket: RequisiteClientSocket, packet: JsonObject, data: JsonObject) {
        if (data.hasKey("uuid") && data.hasKey("owned") && data.hasKey("enabled")) {
            val ownedElement = data["owned"]
            val enabledElement = data["enabled"]
            if (ownedElement.isJsonArray && enabledElement.isJsonArray) {
                val owned = ownedElement.asJsonArray
                val enabled = enabledElement.asJsonArray

                val ownedCosmetics: MutableList<CosmeticData> = Lists.newArrayList()
                val enabledCosmetics: MutableList<CosmeticData> = Lists.newArrayList()

                for (cosmetic in owned) {
                    val retrieved: CosmeticData = socket.requisite.cosmeticManager.fromId(cosmetic.asString)
                    if (retrieved != null) {
                        ownedCosmetics.add(retrieved)
                    }
                }

                for (cosmetic in enabled) {
                    val retrieved: CosmeticData = socket.requisite.cosmeticManager.fromId(cosmetic.asString)
                    if (retrieved != null) {
                        enabledCosmetics.add(retrieved)
                    }
                }

                socket.requisite.cosmeticManager.playerData[data.getAsString("uuid")] = PlayerCosmeticData(ownedCosmetics, enabledCosmetics)
            }
        }
    }
}