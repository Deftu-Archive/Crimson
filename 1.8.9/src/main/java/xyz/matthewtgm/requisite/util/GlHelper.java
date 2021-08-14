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

package xyz.matthewtgm.requisite.util;

import gg.essential.universal.UResolution;
import org.lwjgl.opengl.GL11;

public class GlHelper {

    public void startScissorBox(int x, int y, int width, int height) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        totalScissor(x, y, width, height);
    }

    public void startScissorBox(float x, float y, float width, float height) {
        startScissorBox((int) x, (int) y, (int) width, (int) height);
    }

    public void endScissorBox() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
    }

    public void totalScissor(double xPosition, double yPosition, double width, double height) {
        GL11.glScissor((int) ((xPosition * UResolution.getWindowWidth()) / UResolution.getScaledWidth()), (int) (((UResolution.getScaledHeight() - (yPosition + height)) * UResolution.getWindowHeight()) / UResolution.getScaledHeight()), (int) (width * UResolution.getWindowWidth() / UResolution.getScaledWidth()), (int) (height * UResolution.getWindowHeight() / UResolution.getScaledHeight()));
    }

}