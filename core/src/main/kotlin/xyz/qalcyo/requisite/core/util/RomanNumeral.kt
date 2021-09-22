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

import java.util.*

// https://stackoverflow.com/a/19759564
class RomanNumeral {

    private val map = TreeMap<Int, String>()
    private val numeralCache: MutableMap<Int, String?> = HashMap()

    fun getCached(number: Int): String? {
        if (numeralCache.containsKey(number)) return numeralCache[number]
        val roman = toRoman(number)
        numeralCache[number] = roman
        return roman
    }

    fun toRoman(number: Int): String? {
        val l = map.floorKey(number)
        return if (number == l) map[number] else map[l].toString() + toRoman(number - l)
    }

    init {
        map[1000] = "M"
        map[900] = "CM"
        map[500] = "D"
        map[400] = "CD"
        map[100] = "C"
        map[90] = "XC"
        map[50] = "L"
        map[40] = "XL"
        map[10] = "X"
        map[9] = "IX"
        map[5] = "V"
        map[4] = "IV"
        map[1] = "I"
    }

}