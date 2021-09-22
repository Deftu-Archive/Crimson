package xyz.qalcyo.requisite.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticManager;
import xyz.qalcyo.requisite.core.cosmetics.ICosmetic;
import xyz.qalcyo.requisite.core.cosmetics.ICosmeticHelper;
import xyz.qalcyo.requisite.core.cosmetics.PlayerCosmeticData;

public class CosmeticHelper implements ICosmeticHelper<AbstractClientPlayer> {

    private CosmeticManager<AbstractClientPlayer> cosmeticManager;

    public void initialize(CosmeticManager<AbstractClientPlayer> cosmeticManager) {
        this.cosmeticManager = cosmeticManager;
    }

    public String getUuid() {
        return Minecraft.getMinecraft().getSession().getProfile().getId().toString();
    }

    public boolean hasCosmetic(AbstractClientPlayer player, ICosmetic<AbstractClientPlayer> cosmetic) {
        String uuid = player.getUniqueID().toString();
        boolean passed = false;

        if (cosmeticManager.getPlayerData().containsKey(uuid)) {
            PlayerCosmeticData cosmeticData = cosmeticManager.getPlayerData().get(uuid);
            if (cosmeticData.getEnabled().contains(cosmetic.data())) {
                passed = true;
            }
        }

        return passed;
    }

}