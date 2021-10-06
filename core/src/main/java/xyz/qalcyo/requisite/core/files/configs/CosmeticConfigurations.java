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

package xyz.qalcyo.requisite.core.files.configs;

import xyz.qalcyo.requisite.core.files.ConfigurationManager;
import xyz.qalcyo.requisite.core.files.IConfigurable;
import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;

public class CosmeticConfigurations implements IConfigurable {

    private Configuration configuration;
    private Subconfiguration subconfiguration;

    private boolean cosmeticsEnabled;

    public void initialize(Configuration configuration) {
        this.configuration = configuration;
        if (!configuration.hasKey("assets/requisite/cosmetics"))
            configuration.createSubconfiguration("assets/requisite/cosmetics").save();
        subconfiguration = configuration.getSubconfiguration("assets/requisite/cosmetics");
    }

    public void save(ConfigurationManager configurationManager) {
        Subconfiguration subconfiguration = configuration();

        if (!subconfiguration.hasKey("cosmetics_enabled")) {
            subconfiguration.add("cosmetics_enabled", true).getParent().getAsConfiguration().save();
        }
    }

    public void load(ConfigurationManager configurationManager) {
        Subconfiguration subconfiguration = configuration();
        cosmeticsEnabled = subconfiguration.hasKey("cosmetics_enabled") && subconfiguration.getAsBoolean("cosmetics_enabled");
    }

    public Subconfiguration configuration() {
        return subconfiguration;
    }

    public boolean areCosmeticsEnabled() {
        return cosmeticsEnabled;
    }

    public void setCosmeticsEnabled(boolean cosmeticsEnabled) {
        this.cosmeticsEnabled = cosmeticsEnabled;

        Subconfiguration subconfiguration = configuration();
        subconfiguration.add("cosmetics_enabled", cosmeticsEnabled).getParent().getAsConfiguration().save();
    }

}