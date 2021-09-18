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

class DateHelper {

    /**
     * Adapted from SkyBlockAddons under MIT license
     * https://github.com/BiscuitDevelopment/SkyblockAddons/blob/development/LICENSE
     *
     * @author Biscuit/Phoube
     */
    val isHalloween: Boolean
        get() {
            val calendar = Calendar.getInstance()
            return calendar[Calendar.MONTH] == Calendar.OCTOBER && calendar[Calendar.DAY_OF_MONTH] == 31
        }

    /**
     * @return Whether the current month is within the spectrum of pride month or not.
     */
    val isLgbtqPrideMonth: Boolean
        get() = Calendar.getInstance()[Calendar.MONTH] == Calendar.JUNE

}