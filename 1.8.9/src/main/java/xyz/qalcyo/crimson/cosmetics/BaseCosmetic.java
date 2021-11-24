/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;

public abstract class BaseCosmetic {

    private final String name, id;
    private final CosmeticSlot slot;

    public BaseCosmetic(String name, String id, CosmeticSlot slot) {
        this.name = name;
        this.id = id.toUpperCase().replace(' ', '_');
        this.slot = slot;
    }

    public BaseCosmetic(String name, CosmeticSlot slot) {
        this(name, name.toUpperCase().replace(' ', '_'), slot);
    }

    public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float tickAge, float netHeadYaw, float netHeadPitch, float scale);
    public void tick(TickState state) {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public CosmeticSlot getSlot() {
        return slot;
    }

    public static enum TickState {
        CLIENT,
        RENDER
    }

}