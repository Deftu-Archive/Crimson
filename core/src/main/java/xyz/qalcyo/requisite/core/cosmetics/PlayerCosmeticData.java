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

package xyz.qalcyo.requisite.core.cosmetics;

import java.util.List;

public class PlayerCosmeticData {

    private final List<CosmeticData> owned;
    private final List<CosmeticData> enabled;

    public PlayerCosmeticData(List<CosmeticData> owned, List<CosmeticData> enabled) {
        this.owned = owned;
        this.enabled = enabled;
    }

    public List<CosmeticData> getOwned() {
        return owned;
    }

    public List<CosmeticData> getEnabled() {
        return enabled;
    }

}