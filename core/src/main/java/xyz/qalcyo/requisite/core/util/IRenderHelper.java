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

package xyz.qalcyo.requisite.core.util;

public interface IRenderHelper {

    /* Will be re-added at a later date.
    TODO: 2021/08/20

    void drawRoundedRect(int x, int y, int width, int height, int radius, int colour);
    void drawHollowRoundedRect(int x, int y, int width, int height, int thickness, int colour);

    void drawArc(int x, int y, int radius, int start, int end, Color colour);
    void drawArc(int x, int y, int radius, int start, int end, int colour);
    void drawHollowArc(int x, int y, int radius, int start, int end, int thickness, Color colour);
    void drawHollowArc(int x, int y, int radius, int start, int end, int thickness, int colour);

    */

    /**
     * Draw a rectangle based on the given parameters.
     */
    void drawRect(int left, int top, int right, int bottom, int colour);

    /**
     * Draw a rectangle based on the given parameters.
     */
    void drawRectEnhanced(int x, int y, int width, int height, int colour);

    /**
     * Draw a hollow rectangle based on the given parameters.
     */
    void drawHollowRect(int x, int y, int width, int height, int thickness, int colour);

    /**
     * Draw a horizontal based on the given parameters.
     */
    void drawHorizontalLine(int start, int end, int y, int thickness, int colour);

    /**
     * Draw a vertical line based on the given parameters.
     */
    void drawVerticalLine(int x, int start, int end, int thickness, int colour);
}