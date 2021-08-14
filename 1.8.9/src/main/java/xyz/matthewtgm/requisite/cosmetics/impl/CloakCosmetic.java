/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.matthewtgm.requisite.cosmetics.impl;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import xyz.matthewtgm.requisite.core.cosmetics.rendering.CosmeticContext;
import xyz.matthewtgm.requisite.core.cosmetics.rendering.CosmeticLayer;

public class CloakCosmetic extends CosmeticLayer {

    private final CloakModel model;

    public CloakCosmetic(String name, String id) {
        super(name, id);
        this.model = new CloakModel();
    }

    public CloakCosmetic(String name) {
        super(name);
        this.model = new CloakModel();
    }

    public void render(CosmeticContext context) {
        if (!context.isPlayerInvisible() && context.isPlayerWearingPart("CAPE") && !context.doesPlayerHaveCape()) {
            context.colour(1, 1, 1, 1);
            context.push();
            context.translate(0f, 0f, 0.125f);

            double d0 = context.getPlayerPrevChasingPosX() + (context.getPlayerChasingPosX() - context.getPlayerPrevChasingPosX()) * context.partialTicks() - (context.getPlayerPrevPosX() + (context.getPlayerPosX() - context.getPlayerPrevPosX()) * context.partialTicks());
            double d1 = context.getPlayerPrevChasingPosY() + (context.getPlayerChasingPosY() - context.getPlayerPrevChasingPosY()) * context.partialTicks() - (context.getPlayerPrevPosY() + (context.getPlayerPosY() - context.getPlayerPrevPosY()) * context.partialTicks());
            double d2 = context.getPlayerPrevChasingPosZ() + (context.getPlayerChasingPosZ() - context.getPlayerPrevChasingPosZ()) * context.partialTicks() - (context.getPlayerPrevPosZ() + (context.getPlayerPosZ() - context.getPlayerPrevPosZ()) * context.partialTicks());

            float f = context.getPlayerPrevRenderYawOffset() + (context.getPlayerRenderYawOffset() - context.getPlayerPrevRenderYawOffset()) * context.partialTicks();

            double d3 = MathHelper.sin(f * (float) Math.PI / 180f);
            double d4 = (-MathHelper.cos(f * (float) Math.PI / 180f));

            float f1 = (float) d1 * 10f;
            f1 = MathHelper.clamp_float(f1, -6f, 32f);
            float f2 = (float) (d0 * d3 + d2 * d4) * 100f;
            float f3 = (float) (d0 * d4 - d2 * d3) * 100f;

            if (f2 < 0f)
                f2 = 0f;
            if (f > 180f)
                f2 = 180f;

            float f4 = context.getPlayerPrevCameraYaw() + (context.getPlayerCameraYaw() - context.getPlayerPrevCameraYaw()) * context.partialTicks();
            f1 = f1 + MathHelper.sin((context.getPlayerPrevDistanceWalkedModified() + (context.getPlayerDistanceWalkedModified() - context.getPlayerPrevDistanceWalkedModified()) * context.partialTicks()) * 6f) * 32f * f4;

            if (context.isPlayerSneaking())
                f1 += 25f;

            context.rotate(6f + f2 / 2f + f1, 1f, 0f, 0f);
            context.rotate(f3 / 2f, 0f, 0f, 1f);
            context.rotate(-f3 / 2f, 0f, 1f, 0f);
            context.rotate(180f, 0f, 1f, 0f);

            model.render(context.scale());

            context.pop();
        }
    }

    public void tick() {

    }

    private static class CloakModel extends ModelBase {

        private final ModelRenderer renderer = new ModelRenderer(this, 0, 0);

        public CloakModel() {
            renderer.setTextureOffset(64, 32);
            renderer.addBox(-5f, 05, -1f, 10, 16, 1);
        }

        public void render(float scale) {
            renderer.render(scale);
        }

    }

}