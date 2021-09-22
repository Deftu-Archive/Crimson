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

package xyz.qalcyo.requisite.cosmetics.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData;
import xyz.qalcyo.requisite.cosmetics.BaseCosmetic;

public class CloakCosmetic extends BaseCosmetic {

    private final CloakModel model;

    public CloakCosmetic(CosmeticData cosmeticData) {
        super(cosmeticData);

        this.model = new CloakModel();
    }

    public void render(AbstractClientPlayer player, float partialTicks) {
        if (player.hasPlayerInfo() && !player.isInvisible() && player.isWearing(EnumPlayerModelParts.CAPE) && player.getLocationCape() == null) {
            GlStateManager.color(1f, 1f, 1f, 1f);
            Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0f, 0f, 0.125f);
            double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double)partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks);
            double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double)partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks);
            double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double)partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks);
            float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
            double d3 = MathHelper.sin(f * (float)Math.PI / 180f);
            double d4 = (-MathHelper.cos(f * (float)Math.PI / 180f));
            float f1 = (float)d1 * 10f;
            f1 = MathHelper.clamp_float(f1, -6f, 32f);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100f;
            float f3 = (float)(d0 * d4 - d2 * d3) * 100f;

            if (f2 < 0f) {
                f2 = 0f;
            }

            if (f2 > 180f) {
                f2 = 180f;
            }

            float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
            f1 = f1 + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6f) * 32f * f4;

            if (player.isSneaking()) {
                f1 += 25f;
            }

            GlStateManager.rotate(6f + f2 / 2f + f1, 1f, 0f, 0f);
            GlStateManager.rotate(f3 / 2f, 0f, 0f, 1f);
            GlStateManager.rotate(-f3 / 2f, 0f, 1f, 0f);
            GlStateManager.rotate(180f, 0f, 1f, 0f);
            model.render();
            GlStateManager.popMatrix();
        }
    }

    private static class CloakModel extends ModelBase {
        private final ModelRenderer cape = new ModelRenderer(this, 0, 0);
        public CloakModel() {
            cape.setTextureSize(64, 32);
            cape.addBox(-5f, 0f, -1f, 10, 16, 1);
        }

        public void render() {
            cape.render(0.0625f);
        }

    }

}