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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingHelper {
    /**
     * Creates a Logger instance from the given parameters.
     * @return The Logger instance.
     */
    public Logger create(String prefix, String name) {
        return LogManager.getLogger(prefix + " (" + name + ")");
    }

    /**
     * Creates a Logger instance from the given parameter.
     * @return The Logger instance.
     */
    public Logger create(String name) {
        return LogManager.getLogger(name);
    }

}