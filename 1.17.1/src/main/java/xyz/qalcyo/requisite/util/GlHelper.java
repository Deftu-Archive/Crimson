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

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public class GlHelper {

    public void startScissorBox(int x, int y, int width, int height) {
        totalScissor(x, y, width, height);
    }

    public void startScissorBox(float x, float y, float width, float height) {
        startScissorBox((int) x, (int) y, (int) width, (int) height);
    }

    public void endScissorBox() {
        RenderSystem.disableScissor();
    }

    public void totalScissor(double xPosition, double yPosition, double width, double height) {
        Window window = MinecraftClient.getInstance().getWindow();
        int windowWidth = window.getWidth();
        int scaledWidth = window.getScaledWidth();
        int windowHeight = window.getHeight();
        int scaledHeight = window.getScaledHeight();
        RenderSystem.enableScissor((int) ((xPosition * windowWidth) / scaledWidth), (int) (((scaledHeight - (yPosition + height)) * windowHeight) / scaledHeight), (int) (width * windowWidth / scaledWidth), (int) (height * windowHeight / scaledHeight));
    }

}