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

object MathHelper {

    fun clamp01(value: Float): Float {
        if (value.toDouble() < 0.0) return 0.0f
        return if (value.toDouble() > 1.0) 1f else value
    }

    fun clamp(`val`: Float, min: Float, max: Float): Float {
        var `val` = `val`
        if (`val` > max) `val` = max else if (`val` < min) `val` = min
        return `val`
    }

    fun clamp_int(num: Int, min: Int, max: Int) =
        if (num < min) min else num.coerceAtMost(max)

    fun clamp_long(num: Long, min: Long, max: Long) =
        if (num < min) min else num.coerceAtMost(max)

    fun lerp(start: Float, end: Float, interpolation: Float) =
        start + (end - start) * clamp01(interpolation)

    fun percentageOf(`val`: Float, min: Float, max: Float) =
        (`val` - min) / (max - min)

    fun percentageOf_int(`val`: Int, min: Int, max: Int) =
        (`val` - min) / (max - min)

    fun isBetween(`val`: Int, min: Int, max: Int) =
        `val` in (min + 1) until max

}