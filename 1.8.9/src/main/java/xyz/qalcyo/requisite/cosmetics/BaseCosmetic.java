package xyz.qalcyo.requisite.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData;
import xyz.qalcyo.requisite.core.cosmetics.ICosmetic;

public abstract class BaseCosmetic implements ICosmetic<AbstractClientPlayer> {

    protected final CosmeticData cosmeticData;
    protected ResourceLocation texture;

    public BaseCosmetic(CosmeticData cosmeticData, String fileExtension) {
        this.cosmeticData = cosmeticData;
        this.texture = new ResourceLocation("requisite:dynamic", data().getTexture().name);
    }

    public BaseCosmetic(CosmeticData cosmeticData) {
        this(cosmeticData, "png");
    }

    public abstract void render(AbstractClientPlayer player, float partialTicks);

    public CosmeticData data() {
        return cosmeticData;
    }

}