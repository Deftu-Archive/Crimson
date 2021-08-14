package xyz.matthewtgm.requisite.core.hypixel;

import xyz.matthewtgm.requisite.core.hypixel.api.HypixelAPI;
import xyz.matthewtgm.requisite.core.hypixel.locraw.HypixelLocrawManager;

public class HypixelManager {

    private final HypixelLocrawManager locrawManager = new HypixelLocrawManager();
    private final HypixelAPI api = new HypixelAPI();

    public HypixelLocrawManager getLocrawManager() {
        return locrawManager;
    }

    public HypixelAPI getApi() {
        return api;
    }

}