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