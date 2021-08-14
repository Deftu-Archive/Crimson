package xyz.matthewtgm.requisite.cosmetics;

import gg.essential.universal.UMinecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import xyz.matthewtgm.requisite.core.cosmetics.CosmeticManager;
import xyz.matthewtgm.requisite.core.cosmetics.ICosmeticInitializer;

public class CosmeticInitializer implements ICosmeticInitializer {

    public void initialize(CosmeticManager manager, String playerUuid) {

    }

    private void addLayer(LayerRenderer<AbstractClientPlayer> layer) {
        UMinecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(layer);
        UMinecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(layer);
    }

}