package xyz.matthewtgm.requisite.core.util;

import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.util.JsonApiHelper;

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