package xyz.qalcyo.requisite.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticManager;
import xyz.qalcyo.requisite.core.cosmetics.ICosmetic;
import xyz.qalcyo.requisite.core.cosmetics.ICosmeticRenderer;

import java.util.List;

public class CosmeticRenderer implements LayerRenderer<AbstractClientPlayer>, ICosmeticRenderer<AbstractClientPlayer> {

    private CosmeticManager<AbstractClientPlayer> cosmeticManager;
    private List<ICosmetic<AbstractClientPlayer>> cosmetics;

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float tickAge, float netHeadYaw, float netHeadPitch, float scale) {
        if (cosmetics != null) {
            for (ICosmetic<AbstractClientPlayer> cosmetic : cosmetics) {
                cosmetic.render(cosmeticManager, player, partialTicks);
            }
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public void initialize(CosmeticManager<AbstractClientPlayer> cosmeticManager, List<ICosmetic<AbstractClientPlayer>> cosmetics) {
        this.cosmeticManager = cosmeticManager;
        this.cosmetics = cosmetics;
    }

}