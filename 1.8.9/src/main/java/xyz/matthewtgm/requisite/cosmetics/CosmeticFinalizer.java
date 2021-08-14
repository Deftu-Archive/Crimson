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

package xyz.matthewtgm.requisite.cosmetics;

import gg.essential.universal.UMinecraft;
import xyz.matthewtgm.requisite.core.cosmetics.CosmeticManager;
import xyz.matthewtgm.requisite.core.cosmetics.ICosmeticFinalizer;
import xyz.matthewtgm.requisite.core.cosmetics.rendering.CosmeticLayer;
import xyz.matthewtgm.requisite.cosmetics.impl.CloakCosmetic;

import java.util.List;

public class CosmeticFinalizer implements ICosmeticFinalizer {

    public void initialize(CosmeticManager cosmeticManager, List<CosmeticLayer> cosmetics) {
        cosmetics.add(new CloakCosmetic("Test", "TEST"));
    }

    public void finalize(CosmeticLayer layer) {
        addLayer(layer);
    }

    private void addLayer(CosmeticLayer layer) {
        CosmeticLayerWrapper wrapper = new CosmeticLayerWrapper(layer);

        UMinecraft.getMinecraft().getRenderManager().getSkinMap().get("default").addLayer(wrapper);
        UMinecraft.getMinecraft().getRenderManager().getSkinMap().get("slim").addLayer(wrapper);
    }

}