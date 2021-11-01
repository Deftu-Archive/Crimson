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

package xyz.qalcyo.requisite.core.configs;

import xyz.qalcyo.requisite.core.configs.impl.RequisiteConfig;
import xyz.qalcyo.requisite.core.configs.impl.RequisiteOnboardingConfig;
import xyz.qalcyo.requisite.core.configs.impl.children.MenuConfig;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticConfig;

import java.io.File;

public class ConfigManager {

    private final RequisiteConfig average;
    private final MenuConfig menu;
    private final CosmeticConfig cosmetic;
    private final RequisiteOnboardingConfig onboarding;

    public ConfigManager(File directory) {
        this.average = new RequisiteConfig("config", directory);
        this.average.addChild(this.menu = new MenuConfig());
        this.average.addChild(this.cosmetic = new CosmeticConfig());
        this.onboarding = new RequisiteOnboardingConfig("onboarding", directory);
    }

    public RequisiteConfig getAverage() {
        return average;
    }

    public MenuConfig getMenu() {
        return menu;
    }

    public CosmeticConfig getCosmetic() {
        return cosmetic;
    }

    public RequisiteOnboardingConfig getOnboarding() {
        return onboarding;
    }

}