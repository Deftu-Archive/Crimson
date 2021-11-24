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

import xyz.qalcyo.mango.Lists;

import java.util.List;

public class PlayerCosmeticHolder {

    private final String uuid;
    private List<BaseCosmetic> owned = Lists.newArrayList(), enabled = Lists.newArrayList();

    public PlayerCosmeticHolder(String uuid, List<BaseCosmetic> owned, List<BaseCosmetic> enabled) {
        this.uuid = uuid;
        this.owned.addAll(owned);
        this.enabled.addAll(enabled);
    }

    public String getUuid() {
        return uuid;
    }

    public List<BaseCosmetic> getOwned() {
        return owned;
    }

    public void setOwned(List<BaseCosmetic> owned) {
        this.owned = owned;
    }

    public void addOwned(BaseCosmetic cosmetic) {
        owned.add(cosmetic);
    }

    public List<BaseCosmetic> getEnabled() {
        return enabled;
    }

    public void setEnabled(List<BaseCosmetic> enabled) {
        this.enabled = enabled;
    }

    public void addEnabled(BaseCosmetic cosmetic) {
        enabled.add(cosmetic);
    }

}