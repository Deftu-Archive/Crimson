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

package xyz.qalcyo.requisite.core.util

import java.awt.Color

object ColourHelper {

    /**
     * @return A changing colour based on the users' computer time. Simulates a "chroma" colour.
     */
    fun timeBasedChroma(): Int {
        val l = System.currentTimeMillis()
        return Color.HSBtoRGB(l % 2000L / 2000.0f, 0.8f, 0.8f)
    }

    /**
     * @return Generated chroma based on the coordinate provided.
     */
    fun getChroma(x: Double, y: Double): Color {
        val v = 2000.0f
        return Color(Color.HSBtoRGB(((System.currentTimeMillis() - x * 10.0 * 1.0 - y * 10.0 * 1.0) % v).toFloat() / v, 0.8f, 0.8f))
    }

    /**
     * @return The alpha of the given colour.
     */
    fun getAlpha(colour: Int): Int =
        colour shr 24 and 255

}