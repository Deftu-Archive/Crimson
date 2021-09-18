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

package xyz.qalcyo.requisite.core.hypixel.locraw

import xyz.qalcyo.json.entities.JsonObject
import xyz.qalcyo.json.serialization.JsonSerializer

class HypixelLocraw(serverId: Any?, gameMode: Any?, mapName: Any?, rawGameType: Any?) {

    /* Metadata */
    val serverId: String
    val gameMode: String
    val mapName: String
    val rawGameType: String

    val gameType: GameType

    fun toJson(): JsonObject =
        JsonSerializer.create(this)

    override fun toString(): String =
        toJson().asString

    enum class GameType(val serverName: String) {
        UNKNOWN(""),
        LIMBO("LIMBO"),
        BEDWARS("BEDWARS"),
        SKYWARS("SKYWARS"),
        PROTOTYPE("PROTOTYPE"),
        SKYBLOCK("SKYBLOCK"),
        MAIN("MAIN"),
        MURDER_MYSTERY("MURDER_MYSTERY"),
        HOUSING("HOUSING"),
        ARCADE_GAMES("ARCADE"),
        BUILD_BATTLE("BUILD_BATTLE"),
        DUELS("DUELS"),
        PIT("PIT"),
        UHC_CHAMPIONS("UHC"),
        SPEED_UHC("SPEED_UHC"),
        TNT_GAMES("TNTGAMES"),
        CLASSIC_GAMES("LEGACY"),
        COPS_AND_CRIMS("MCGO"),
        BLITZ_SG("SURVIVAL_GAMES"),
        MEGA_WALLS("WALLS3"),
        SMASH_HEROES("SUPER_SMASH"),
        WARLORDS("BATTLEGROUND");

        companion object {
            fun getFromLocraw(gameType: String): GameType {
                for (value in values()) if (value.serverName == gameType) return value
                return UNKNOWN
            }
        }
    }

    companion object {
        /* Constants */
        val LIMBO = HypixelLocraw("limbo", null, null, "LIMBO")
    }

    init {
        this.serverId = serverId?.toString() ?: ""
        this.gameMode = gameMode?.toString() ?: ""
        this.mapName = mapName?.toString() ?: ""
        this.rawGameType = rawGameType?.toString() ?: ""
        gameType = GameType.getFromLocraw(this.rawGameType)
    }

}