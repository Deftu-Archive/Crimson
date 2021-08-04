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

package xyz.matthewtgm.requisite.core;

import lombok.Getter;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.relauncher.CoreModManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.launch.MixinTweaker;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.util.List;

public class RequisiteLauncher {

    @Getter private static boolean initialized = false;

    public static void initialize() {
        if (initialized)
            return;
        initialized = true;
        Logger logger = LogManager.getLogger("Requisite (Launcher)");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.requisite.json");

        try {
            injectMixinTweaker(logger);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* https://github.com/Mouse0w0/forge-mixin-example/blob/08a51985822736f51e0b77a58850ff3abc4628bd/src/main/java/com/yourname/modid/core/CoreMod.java */
        CodeSource source = RequisiteLauncher.class.getProtectionDomain().getCodeSource();
        if (source != null) {
            URL location = source.getLocation();
            try {
                File sourceFile = new File(location.toURI());
                if (sourceFile.isFile()) {
                    CoreModManager.getIgnoredMods().remove(sourceFile.getName());
                    CoreModManager.getReparseableCoremods().remove(sourceFile.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.warn("No CodeSource, if this isn't a development environment we may run into problems!");
        }
    }

    private static void injectMixinTweaker(Logger logger) {
        String mixinTweaker = MixinTweaker.class.getName();
        List<String> tweakClasses = (List<String>) Launch.blackboard.get("TweakClasses");
        if (!tweakClasses.contains(mixinTweaker)) {
            try {
                logger.info("Requisite was unable to find mixin, attempting to inject the MixinTweaker.");
                Launch.classLoader.addClassLoaderExclusion(mixinTweaker.substring(0, mixinTweaker.lastIndexOf('.')));
                tweakClasses.add(mixinTweaker);
                logger.info("Requisite was successfully able to inject the MixinTweaker.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Requisite has detected mixin, not injecting the MixinTweaker.");
        }
    }

}