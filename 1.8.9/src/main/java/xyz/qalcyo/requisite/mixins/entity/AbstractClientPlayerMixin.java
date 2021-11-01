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

package xyz.qalcyo.requisite.mixins.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.cosmetics.BaseCosmetic;
import xyz.qalcyo.requisite.cosmetics.PlayerCosmeticHolder;
import xyz.qalcyo.requisite.cosmetics.impl.CloakCosmetic;

import java.util.List;
import java.util.Objects;

@Mixin({AbstractClientPlayer.class})
public class AbstractClientPlayerMixin {

    @Inject(method = "getLocationCape", at = @At("HEAD"), cancellable = true)
    private void modifyLocationCape(CallbackInfoReturnable<ResourceLocation> cir) {
        String uuid = ((Entity) (Object) this).getUniqueID().toString();
        if (!Requisite.getInstance().getConfigManager().getCosmetic().isShowOwnCosmetics() && Objects.equals(uuid, Minecraft.getMinecraft().getSession().getProfile().getId().toString()))
            return;
        if (Requisite.getInstance().getCosmeticManager().getPlayerData().containsKey(uuid)) {
            PlayerCosmeticHolder cosmeticHolder = Requisite.getInstance().getCosmeticManager().getPlayerData().get(uuid);
            if (cosmeticHolder != null && cosmeticHolder.getEnabled() != null && !cosmeticHolder.getEnabled().isEmpty()) {
                List<BaseCosmetic> enabled = cosmeticHolder.getEnabled();
                for (BaseCosmetic cosmetic : enabled) {
                    if (cosmetic instanceof CloakCosmetic) {
                        cir.setReturnValue(null);
                    }
                }
            }
        }
    }

}