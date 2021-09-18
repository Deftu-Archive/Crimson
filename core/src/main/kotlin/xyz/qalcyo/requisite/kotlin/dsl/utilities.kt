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

package xyz.qalcyo.requisite.kotlin.dsl

import xyz.qalcyo.json.entities.JsonElement
import xyz.qalcyo.json.util.JsonHelper
import xyz.qalcyo.requisite.core.data.ColourRGB
import java.awt.Color
import java.util.*

/* Object/Any */
fun Any.stringify(): String = Objects.toString(this)

/* Numbers */
fun Number.isPositive(): Boolean = this.toInt() >= 0f
fun Number.isNegative(): Boolean = this.toInt() < 0
fun Number.isZero(): Boolean = this.toInt() == 0
fun Number.percentage(min: Int, max: Int): Int = (this.toInt() - min) / (max - min)
fun Number.isBetween(min: Int, max: Int): Boolean = this.toInt() in (min + 1) until max

/* java.awt.Color */
fun Color.toColourRGB(): ColourRGB =
    ColourRGB(red, green, blue, alpha)

/* xyz.matthewtgm.json.entities.JsonElement */
fun JsonElement.prettify(): String = JsonHelper.makePretty(this)