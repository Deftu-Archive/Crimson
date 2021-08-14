package xyz.matthewtgm.requisite.hypixel.api;

import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;

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