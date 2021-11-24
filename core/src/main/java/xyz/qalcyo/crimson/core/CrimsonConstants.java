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

package xyz.qalcyo.crimson.core;

/**
 * Stores persistent data for Crimson.
 */
public class CrimsonConstants {

    protected static CrimsonAPI INSTANCE;

    public static final int SOCKET_VERSION = 1;
    public static final String CREDITS_URL = "https://raw.githubusercontent.com/Qalcyo/DataStorage/master/crimson/credits.json";
    public static final String CHANGELOG_URL_UNFORMATTED = "https://raw.githubusercontent.com/Qalcyo/DataStorage/master/crimson/changelogs/%s.md";

    /**
     * @return A cached instance of Crimson.
     */
    public static CrimsonAPI getInstance() {
        return INSTANCE;
    }

}