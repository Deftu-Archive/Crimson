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
import net.minecraft.util.ResourceLocation;
import xyz.qalcyo.requisite.core.cosmetics.*;
import xyz.qalcyo.requisite.cosmetics.impl.AnimatedCloakCosmetic;
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

        if (data.isType(CosmeticType.CLOAK)) {
            if (data.hasFlag(CosmeticFlag.ANIMATED)) {
                cosmetics.add(new AnimatedCloakCosmetic(data));
            } else {
                cosmetics.add(new CloakCosmetic(data));
            }
        }
    }

    public void finish(CosmeticManager<AbstractClientPlayer> cosmeticManager, List<ICosmetic<AbstractClientPlayer>> cosmetics) {
        renderer.initialize(cosmeticManager, cosmetics);

        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(renderer);
        Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(renderer);
    }

    public CosmeticRenderer renderer() {
        return renderer;
    }

}