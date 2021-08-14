package xyz.matthewtgm.requisite.hypixel;

import xyz.matthewtgm.requisite.hypixel.api.HypixelAPI;
import xyz.matthewtgm.requisite.hypixel.locraw.HypixelLocrawManager;

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