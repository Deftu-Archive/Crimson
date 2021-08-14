package xyz.matthewtgm.requisite.hypixel;

import gg.essential.universal.UMinecraft;
import gg.essential.universal.wrappers.UPlayer;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.hypixel.api.HypixelAPI;
import xyz.matthewtgm.requisite.hypixel.locraw.HypixelLocrawManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HypixelManager {

    private static final Pattern SERVER_BRAND_PATTERN = Pattern.compile("(.+) <- (?:.+)");

    private final HypixelLocrawManager locrawManager;
    private final HypixelAPI api = new HypixelAPI();

    public HypixelManager(IRequisite requisite) {
        locrawManager = new HypixelLocrawManager(requisite);
    }

    public HypixelLocrawManager getLocrawManager() {
        return locrawManager;
    }

    public HypixelAPI getApi() {
        return api;
    }

    public boolean isOnHypixel() {
        String HYPIXEL_SERVER_BRAND = "Hypixel BungeeCord";

        if (!UMinecraft.getMinecraft().isSingleplayer() && UPlayer.hasPlayer() && UPlayer.getPlayer().getClientBrand() != null) {
            Matcher matcher = SERVER_BRAND_PATTERN.matcher(UPlayer.getPlayer().getClientBrand());

            if (matcher.find())
                return matcher.group(1).startsWith(HYPIXEL_SERVER_BRAND);
            else
                return false;
        } else
            return false;
    }

}