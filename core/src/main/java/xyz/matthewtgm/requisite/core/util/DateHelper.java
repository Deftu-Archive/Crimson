package xyz.matthewtgm.requisite.core.util;

import java.util.Calendar;

public class DateHelper {

    /**
     * Taken from SkyBlockAddons under MIT license
     * https://github.com/BiscuitDevelopment/SkyblockAddons/blob/development/LICENSE
     *
     * @author Biscuit/Phoube
     */
    public static boolean isHalloween() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) == Calendar.OCTOBER && calendar.get(Calendar.DAY_OF_MONTH) == 31;
    }

}