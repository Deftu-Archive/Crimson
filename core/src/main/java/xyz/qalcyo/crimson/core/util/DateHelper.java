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

import java.util.Calendar;

public class DateHelper {

    /**
     * Adapted from SkyBlockAddons under MIT license
     * https://github.com/BiscuitDevelopment/SkyblockAddons/blob/development/LICENSE
     *
     * @author Biscuit/Phoube
     */
    public boolean isHalloween() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) == Calendar.OCTOBER && calendar.get(Calendar.DAY_OF_MONTH) == 31;
    }

    /**
     * @return Whether the current month and day is within the spectrum of the standard holiday season.
     */
    public boolean isHolidaySeason() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) > 4;
    }

    /**
     * @return Whether the current month is within the spectrum of pride month or not.
     */
    public boolean isPrideMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) == Calendar.JUNE;
    }

}