package xyz.deftu.requisite.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import xyz.deftu.requisite.core.cosmetics.CosmeticData;
import xyz.deftu.requisite.core.cosmetics.ICosmetic;

public abstract class BaseCosmetic implements ICosmetic<AbstractClientPlayer> {

    protected final CosmeticData cosmeticData;

    public BaseCosmetic(CosmeticData cosmeticData) {
        this.cosmeticData = cosmeticData;
    }

    public abstract void render(AbstractClientPlayer player, float partialTicks);

    public CosmeticData data() {
        return cosmeticData;
    }

}