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

package xyz.matthewtgm.requisite.hypixel.locraw;

import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.serialization.JsonSerializer;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeExcluded;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeName;

public class HypixelLocraw {

    /* Constants */
    public static final HypixelLocraw LIMBO = new HypixelLocraw("limbo", null, null, "LIMBO");

    /* Metadata */
    @JsonSerializeName("server")
    private final String serverId;
    @JsonSerializeName("mode")
    private final String gameMode;
    @JsonSerializeName("map")
    private final String mapName;
    @JsonSerializeName("gametype")
    private final String rawGameType;
    @JsonSerializeExcluded
    private final GameType gameType;

    public JsonObject toJson() {
        return JsonSerializer.create(this);
    }

    public String toString() {
        return toJson().getAsString();
    }

    public HypixelLocraw(Object serverId, Object gameMode, Object mapName, Object rawGameType) {
        this.serverId = serverId == null ? "" : serverId.toString();
        this.gameMode = gameMode == null ? "" : gameMode.toString();
        this.mapName = mapName == null ? "" : mapName.toString();
        this.rawGameType = rawGameType == null ? "" : rawGameType.toString();
        this.gameType = GameType.getFromLocraw(this.rawGameType);
    }

    public enum GameType {

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

        private final String serverName;
        GameType(String serverName) {
            this.serverName = serverName;
        }

        public static GameType getFromLocraw(String gameType) {
            for (GameType value : values())
                if (value.serverName.equals(gameType))
                    return value;
            return UNKNOWN;
        }

        public String getServerName() {
            return serverName;
        }

    }

    public String getServerId() {
        return serverId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getMapName() {
        return mapName;
    }

    public String getRawGameType() {
        return rawGameType;
    }

    public GameType getGameType() {
        return gameType;
    }

}