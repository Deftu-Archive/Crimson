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

package xyz.qalcyo.requisite.dsl

import xyz.qalcyo.json.entities.JsonElement
import xyz.qalcyo.json.util.JsonHelper
import xyz.qalcyo.mango.data.Colour
import xyz.qalcyo.requisite.core.data.ColourRGB
import java.awt.Color
import java.util.*

/* Object/Any */

/**
 * @return A "stringified" version of the object.
 */
fun Any.stringify(): String = Objects.toString(this)

/* Numbers */

/**
 * @return Whether the number is positive.
 */
fun Number.isPositive(): Boolean = toInt() >= 0f

/**
 * @return Whether the number is negative.
 */
fun Number.isNegative(): Boolean = toInt() < 0

/**
 * @return Whether the number is zero.
 */
fun Number.isZero(): Boolean = toInt() == 0

/**
 * @return A percentage value of the number provided between params min and max.
 */
fun Number.percentage(min: Int, max: Int): Int = (toInt() - min) / (max - min)

/**
 * @return Whether the number is between params min and max.
 */
fun Number.isBetween(min: Int, max: Int): Boolean = toInt() in (min + 1) until max

/* Colours */

/**
 * @return A ColourRGB instance which represents the Color's values.
 */
fun Color.toColourRGB(): ColourRGB = ColourRGB(red, green, blue, alpha)

/**
 * @return A ColourRGB instance which represents the Colour's values.
 */
fun Colour.toColourRGB(): ColourRGB = ColourRGB(red, green, blue, alpha)

/**
 * @return A prettified String of a JsonElement.
 */
fun JsonElement.prettify(): String = JsonHelper.makePretty(this)