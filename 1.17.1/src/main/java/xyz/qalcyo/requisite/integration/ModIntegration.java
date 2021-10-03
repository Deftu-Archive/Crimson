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

package xyz.qalcyo.requisite.integration;

import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.integration.mods.IMod;
import xyz.qalcyo.requisite.core.integration.mods.IModIntegration;
import xyz.qalcyo.requisite.core.integration.mods.ModMetadata;

import java.util.ArrayList;
import java.util.List;

public class ModIntegration implements IModIntegration {

    private final IRequisite requisite;
    private final List<IMod> integratedMods = new ArrayList<>();

    public ModIntegration(IRequisite requisite) {
        this.requisite = requisite;
    }

    public void registerIntegratedMod(IMod mod) {
        ModMetadata metadata = mod.getMetadata();
        requisite.getLogger().info("Mod " + metadata.name + " v" + metadata.version + " was registered as an integrated @NAME@ mod.");
        integratedMods.add(mod);
    }

    public List<IMod> getIntegratedMods() {
        return integratedMods;
    }

}