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

package xyz.qalcyo.crimson.core.configs;

import xyz.qalcyo.crimson.core.configs.impl.CrimsonConfig;
import xyz.qalcyo.crimson.core.configs.impl.CrimsonOnboardingConfig;
import xyz.qalcyo.crimson.core.configs.impl.children.MenuConfig;
import xyz.qalcyo.crimson.core.cosmetics.CosmeticConfig;

import java.io.File;

public class ConfigManager {

    private final CrimsonConfig average;
    private final MenuConfig menu;
    private final CosmeticConfig cosmetic;
    private final CrimsonOnboardingConfig onboarding;

    public ConfigManager(File directory) {
        this.average = new CrimsonConfig("config", directory);
        this.average.addChild(this.menu = new MenuConfig());
        this.average.addChild(this.cosmetic = new CosmeticConfig());
        this.onboarding = new CrimsonOnboardingConfig("onboarding", directory);
    }

    public CrimsonConfig getAverage() {
        return average;
    }

    public MenuConfig getMenu() {
        return menu;
    }

    public CosmeticConfig getCosmetic() {
        return cosmetic;
    }

    public CrimsonOnboardingConfig getOnboarding() {
        return onboarding;
    }

}