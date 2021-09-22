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