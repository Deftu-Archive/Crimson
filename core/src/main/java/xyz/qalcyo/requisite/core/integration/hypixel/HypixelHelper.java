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

package xyz.qalcyo.requisite.core.integration.hypixel;

import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.integration.hypixel.api.HypixelAPI;
import xyz.qalcyo.requisite.core.integration.hypixel.locraw.HypixelLocrawManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HypixelHelper {

    private static final Pattern SERVER_BRAND_PATTERN = Pattern.compile("(.+) <- (?:.+)");

    private final RequisiteAPI requisite;

    private final HypixelLocrawManager locrawManager;
    private final HypixelAPI api = new HypixelAPI();

    public HypixelHelper() {
        this.requisite = RequisiteAPI.retrieveInstance();
        locrawManager = new HypixelLocrawManager(this);
    }

    public HypixelLocrawManager getLocrawManager() {
        return locrawManager;
    }

    public HypixelAPI getApi() {
        return api;
    }

    public boolean isOnHypixel() {
        String HYPIXEL_SERVER_BRAND = "Hypixel BungeeCord";

        if (!requisite.getServerHelper().isSingleplayer() && requisite.getBridge().getMinecraftBridge().isPlayerPresent() && requisite.getServerHelper().getBrand() != null) {
            Matcher matcher = SERVER_BRAND_PATTERN.matcher(requisite.getServerHelper().getBrand());

            if (matcher.find()) {
                return matcher.group(1).startsWith(HYPIXEL_SERVER_BRAND);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}