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

package xyz.qalcyo.requisite.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.core.util.ISupportHelper;

import java.lang.management.ManagementFactory;
import java.util.List;

public class SupportHelper implements ISupportHelper {

    public void copyGameInfo() {
        StringBuilder builder = new StringBuilder();

        builder.append("-- System --").append("\n");
        builder.append("OS: ").append(System.getProperty("os.name")).append("\n");
        builder.append("CPU: ").append(OpenGlHelper.getCpu()).append("\n");
        builder.append("Display: ").append(String.format("%dx%d (%s)", Display.getWidth(), Display.getHeight(), GL11.glGetString(GL11.GL_VENDOR))).append("\n");
        builder.append("GPU: ").append(GL11.glGetString(GL11.GL_RENDERER)).append("\n");
        builder.append("GPU Driver: ").append(GL11.glGetString(GL11.GL_VERSION)).append("\n");
        builder.append("Maximum Memory: ").append(getMemorySize() / 1024 / 1024).append("MB").append("\n");
        builder.append("Shaders: ").append(OpenGlHelper.isFramebufferEnabled() ? "Available" : "Unavailable").append("\n");

        builder.append("\n");

        builder.append("-- JVM --").append("\n");
        builder.append("JVM Version: ").append(String.format("%s %dbit", System.getProperty("java.version"), Minecraft.getMinecraft().isJava64bit() ? 64 : 32)).append("\n");
        builder.append("Memory: ").append(getMemory()).append("\n");

        builder.append("\n");

        builder.append("-- Minecraft --").append("\n");
        builder.append("Game Version: ").append(ForgeVersion.mcVersion).append("\n");
        builder.append("Current FPS: ").append(Minecraft.getDebugFPS()).append("\n");

        builder.append("\n");

        builder.append("-- Mod Loader --").append("\n");
        builder.append("Loader Brand: ").append("Forge").append("\n");
        builder.append("Mods Loaded: ").append(Loader.instance().getActiveModList().size()).append("\n");
        builder.append("Loader Version: ").append(ForgeVersion.getVersion()).append("\n");

        builder.append("\n");

        builder.append("-- Mod List --").append("\n");
        List<ModContainer> modList = Loader.instance().getActiveModList();
        for (ModContainer container : modList) {
            builder.append(container.getName()).append(" - ").append(container.getVersion()).append(" - ").append(container.getSource().getName()).append(modList.indexOf(container) == modList.size() ? "" : "\n");
        }

        Requisite.getInstance().getClipboardHelper().setClipboardString(builder.toString());
    }

    private static String getMemory() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long currentMemory = totalMemory - freeMemory;
        return String.format("% 2d%% %03d/%03dMB", currentMemory * 100L / maxMemory, currentMemory / 1024L / 1024L, maxMemory / 1024L / 1024L);
    }

    private static long getMemorySize() {
        try {
            return ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        } catch (Exception e) {
            try {
                return ((com.sun.management.UnixOperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            } catch (Exception ignored) {
            }
        }
        return -1;
    }

}