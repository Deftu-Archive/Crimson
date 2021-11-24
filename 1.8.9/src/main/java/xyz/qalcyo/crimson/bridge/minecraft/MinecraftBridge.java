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

package xyz.qalcyo.crimson.bridge.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import xyz.qalcyo.mango.Strings;
import xyz.qalcyo.crimson.core.bridge.minecraft.IMinecraftBridge;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

public class MinecraftBridge implements IMinecraftBridge {

    private UUID playerUuid;

    public void initialize() {
        playerUuid = Minecraft.getMinecraft().getSession().getProfile().getId();
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

    public List<String> getCrimsonModList() {
        List<String> value = new ArrayList<>();

        for (URL source : Launch.classLoader.getSources()) {
            if (source != null) {
                try {
                    URI uri = source.toURI();
                    if (uri.getScheme().contains("file")) {
                        File file = new File(uri);

                        String tweaker = null;
                        try {
                            JarFile jar = new JarFile(file);
                            if (jar.getManifest() != null) {
                                Attributes attributes = jar.getManifest().getMainAttributes();
                                tweaker = attributes.getValue("TweakClass");
                            }
                        } catch (Exception ignored) {
                        }

                        if (tweaker != null) {
                            tweaker = tweaker.toLowerCase();
                            if (tweaker.contains("crimson") && tweaker.contains("installer")) {
                                for (ModContainer container : Loader.instance().getModList()) {
                                    if (container != null && container.getName() != null && Strings.containsIgnoreCase(file.getName(), container.getName())) {
                                        value.add(container.getName());
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return value;
    }

}