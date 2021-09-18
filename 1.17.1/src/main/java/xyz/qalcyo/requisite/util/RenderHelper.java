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

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class RenderHelper extends DrawableHelper implements IRenderHelper {

    public void drawRect(MatrixStack matrices, int left, int top, int right, int bottom, int colour) {
        fill(matrices, left, top, right, bottom, colour);
    }

    public void drawRect(int left, int top, int right, int bottom, int colour) {
        drawRect(new MatrixStack(), left, top, right, bottom, colour);
    }

    public void drawRectEnhanced(MatrixStack matrices, int x, int y, int width, int height, int colour) {
        drawRect(matrices, x, y, x + width, y + height, colour);
    }

    public void drawRectEnhanced(int x, int y, int width, int height, int colour) {
        drawRectEnhanced(new MatrixStack(), x, y, width, height, colour);
    }

    public void drawHollowRect(int x, int y, int width, int height, int thickness, int colour) {
        drawHorizontalLine(x, x + width, y, thickness, colour);
        drawHorizontalLine(x, x + width, y + height, thickness, colour);
        drawVerticalLine(x, y + height, y, thickness, colour);
        drawVerticalLine(x + width, y + height, y, thickness, colour);
    }

    public void drawHorizontalLine(MatrixStack matrices, int start, int end, int y, int thickness, int colour) {
        for (int i = 0; i < thickness; i++) {
            drawHorizontalLine(matrices, start, end, y + i, colour);
        }
    }

    public void drawHorizontalLine(int start, int end, int y, int thickness, int colour) {
        drawHorizontalLine(new MatrixStack(), start, end, y, thickness, colour);
    }

    public void drawVerticalLine(MatrixStack matrices, int x, int start, int end, int thickness, int colour) {
        for (int i = 0; i < thickness; i++) {
            drawVerticalLine(matrices, x + i, start, end, colour);
        }
    }

    public void drawVerticalLine(int x, int start, int end, int thickness, int colour) {
        drawVerticalLine(new MatrixStack(), x, start, end, thickness, colour);
    }

}