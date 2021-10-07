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

package xyz.qalcyo.requisite.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.mango.Maps;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.cosmetics.impl.CloakCosmetic;
import xyz.qalcyo.requisite.networking.packets.cosmetics.CosmeticRetrievePacket;

import java.util.List;
import java.util.Map;

public class CosmeticManager {

    private final List<BaseCosmetic> cosmetics = Lists.newCopyOnWriteArrayList();
    private final List<String> checkCache = Lists.newCopyOnWriteArrayList();
    private final Map<String, PlayerCosmeticHolder> playerData = Maps.newHashMap();

    public void start() {
        MinecraftForge.EVENT_BUS.register(this);
        addLayer(new CosmeticRenderer(this));
        initialize();
        check(Requisite.getInstance().getPlayerHelper().getUuid().toString());
    }

    public void initialize() {
        cosmetics.add(new CloakCosmetic("Beehive Cloak", texture("cloaks", "beehive_cloak.png")));
        cosmetics.add(new CloakCosmetic("Booster Cloak", texture("cloaks", "booster_cloak.png")));
        cosmetics.add(new CloakCosmetic("Bug Hunter Cloak", texture("cloaks", "bug_hunter_cloak.png")));
        cosmetics.add(new CloakCosmetic("Developer Cloak", texture("cloaks", "developer_cloak.png")));
        cosmetics.add(new CloakCosmetic("Keycap Cloak", texture("cloaks", "keycap_cloak.png")));
        cosmetics.add(new CloakCosmetic("Modder Cloak", texture("cloaks", "modder_cloak.png")));
        cosmetics.add(new CloakCosmetic("Nopo Cloak", texture("cloaks/exclusive", "nopo_cloak.png")));
        cosmetics.add(new CloakCosmetic("Partner Cloak", texture("cloaks", "partner_cloak.png")));
        cosmetics.add(new CloakCosmetic("Strebbypatty Cloak", "STREB_CLOAK", texture("cloaks/exclusive", "streb_cloak.png")));
        cosmetics.add(new CloakCosmetic("Space Rats Cloak", texture("cloaks", "space_rats_cloak.png")));
    }

    private ResourceLocation texture(String type, String file) {
        return new ResourceLocation(Requisite.getInstance().id(), "cosmetics/" + type + "/" + file);
    }

    private void addLayer(LayerRenderer<AbstractClientPlayer> layerRenderer) {
        Map<String, RenderPlayer> skinMap = Minecraft.getMinecraft().getRenderManager().getSkinMap();

        skinMap.get("default").addLayer(layerRenderer);
        skinMap.get("slim").addLayer(layerRenderer);
    }

    public BaseCosmetic fromId(String id) {
        for (BaseCosmetic cosmetic : cosmetics) {
            if (cosmetic.getId().equals(id)) {
                return cosmetic;
            }
        }

        return null;
    }

    @SubscribeEvent
    public void onEntityJoinedWorld(EntityJoinWorldEvent event) {
        if (checkCache.size() > 100) {
            checkCache.clear();
        }

        String uuid = event.entity.getUniqueID().toString();
        if (event.entity instanceof AbstractClientPlayer && !checkCache.contains(uuid)) {
            check(uuid);
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        for (BaseCosmetic cosmetic : cosmetics) {
            cosmetic.tick(BaseCosmetic.TickState.CLIENT);
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        for (BaseCosmetic cosmetic : cosmetics) {
            cosmetic.tick(BaseCosmetic.TickState.RENDER);
        }
    }

    public void check(String uuid) {
        if (checkCache.contains(uuid)) {
            return;
        }

        Requisite.getInstance().getRequisiteSocket().send(new CosmeticRetrievePacket(uuid));
        checkCache.add(uuid);
    }

    public List<String> getCheckCache() {
        return checkCache;
    }

    public void clearCheckCache() {
        checkCache.clear();
        check(Requisite.getInstance().getPlayerHelper().getUuid().toString());
    }

    public Map<String, PlayerCosmeticHolder> getPlayerData() {
        return playerData;
    }

}