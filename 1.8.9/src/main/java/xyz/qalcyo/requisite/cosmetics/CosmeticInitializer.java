package xyz.qalcyo.requisite.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData;
import xyz.qalcyo.requisite.core.cosmetics.ICosmetic;
import xyz.qalcyo.requisite.core.cosmetics.ICosmeticInitializer;
import xyz.qalcyo.requisite.cosmetics.impl.CloakCosmetic;
import xyz.qalcyo.requisite.textures.InputStreamTexture;

import java.util.List;

public class CosmeticInitializer implements ICosmeticInitializer<AbstractClientPlayer> {

    private final CosmeticRenderer renderer;

    public CosmeticInitializer() {
        this.renderer = new CosmeticRenderer();
    }

    public void initialize(List<ICosmetic<AbstractClientPlayer>> cosmetics, CosmeticData data) {
        Minecraft.getMinecraft().getTextureManager().loadTexture(new ResourceLocation("requisite:dynamic", data.getTexture().name), new InputStreamTexture(data.getTexture().texture));
        cosmetics.add(new CloakCosmetic(data));
    }

    public void finish(List<ICosmetic<AbstractClientPlayer>> cosmetics) {
        renderer.initialize(cosmetics);

        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(renderer);
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(renderer);
    }

    public CosmeticRenderer renderer() {
        return renderer;
    }

}