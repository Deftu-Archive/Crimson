package xyz.matthewtgm.requisite.core.hypixel.locraw;

import lombok.Getter;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.serialization.JsonSerializer;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeExcluded;
import xyz.matthewtgm.json.serialization.annotations.JsonSerializeName;

@Getter
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
    private final HypixelHelper.HypixelLocraw.GameType gameType;

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
        this.gameType = HypixelHelper.HypixelLocraw.GameType.getFromLocraw(this.rawGameType);
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

        @Getter
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
    }
}