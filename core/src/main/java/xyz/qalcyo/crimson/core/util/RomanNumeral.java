/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// https://stackoverflow.com/a/19759564
public final class RomanNumeral {

    private final TreeMap<Integer, String> map = new TreeMap<>();

    {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    private final Map<Integer, String> numeralCache = new HashMap<>();

    /**
     * Either returns the cached roman numeral version of a number or gets the roman numeral, caches it, and returns it.
     * @return The roman numeral value of the number.
     */
    public String getCached(int number) {
        if (numeralCache.containsKey(number))
            return numeralCache.get(number);

        String roman = toRoman(number);
        numeralCache.put(number, roman);
        return roman;
    }

    /**
     * @return The roman numeral value of the number.
     */
    public String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l)
            return map.get(number);
        return map.get(l) + toRoman(number - l);
    }

}