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

package xyz.qalcyo.crimson.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

/**
 * Launches Crimson at game start using it's installer.
 */
public class CrimsonLauncher {

    private static boolean initialized = false;

    /**
     * Initializes Crimson's Mixin configuration.
     */
    public static void initialize() {
        if (initialized) {
            return;
        }

        initialized = true;
        Logger logger = LogManager.getLogger("Crimson (Launcher)");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.crimson.json");
        logger.info("Successfully added Crimson Mixin config.");
    }

    /**
     * @return Whether Crimson's Mixin configuration has been initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }

}