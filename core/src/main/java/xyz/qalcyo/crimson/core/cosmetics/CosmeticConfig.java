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

package xyz.qalcyo.crimson.core.cosmetics;

import xyz.qalcyo.crimson.core.configs.IConfigChild;
import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;

import java.util.ArrayList;
import java.util.List;

public class CosmeticConfig implements IConfigChild {

    private Configuration configuration;
    private List<IConfigChild> children = new ArrayList<>();

    private Subconfiguration self;

    private boolean showOwnCosmetics;

    public String getName() {
        return "cosmetics";
    }

    public void initialize(Configuration configuration, Subconfiguration self) {
        this.configuration = configuration;
        this.self = self;

        if (!self.hasKey("show_self_cosmetics"))
            self.add("show_self_cosmetics", true).getParent().getAsConfiguration().save();
        showOwnCosmetics = self.getAsBoolean("show_self_cosmetics");
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<IConfigChild> getChildren() {
        return children;
    }

    public boolean isShowOwnCosmetics() {
        return showOwnCosmetics;
    }

    public void setShowOwnCosmetics(boolean showOwnCosmetics) {
        this.showOwnCosmetics = showOwnCosmetics;
        self.add("show_self_cosmetics", showOwnCosmetics).getParent().getAsConfiguration().save();
    }

}