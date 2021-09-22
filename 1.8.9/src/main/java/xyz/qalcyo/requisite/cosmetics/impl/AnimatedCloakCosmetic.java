package xyz.qalcyo.requisite.cosmetics.impl;

import net.minecraft.client.entity.AbstractClientPlayer;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData;

public class AnimatedCloakCosmetic extends CloakCosmetic {

    // TODO: 2021/09/17

    public AnimatedCloakCosmetic(CosmeticData cosmeticData) {
        super(cosmeticData);
    }

    public void render(AbstractClientPlayer player, float partialTicks) {
        super.render(player, partialTicks);
    }

}