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
import xyz.qalcyo.mango.Classes;
import xyz.qalcyo.crimson.core.CrimsonAPI;

public class UniversalLogger {

    private final CrimsonAPI crimson;

    public UniversalLogger() {
        this.crimson = CrimsonAPI.retrieveInstance();
    }

    /**
     * Logs an info message via a new customized Logger instance.
     */
    public void info(String str) {
        create().info(str);
    }

    /**
     * Logs a warn message via a new customized Logger instance.
     */
    public void warn(String str) {
        create().warn(str);
    }

    /**
     * Logs an error message via a new customized Logger instance.
     */
    public void error(String str) {
        create().error(str);
    }

    /**
     * Logs an error message that includes a stack trace via a new customized Logger instance.
     */
    public void error(String str, Throwable throwable) {
        create().error(str, throwable);
    }

    /**
     * Logs a debug message via a new customized Logger instance.
     */
    public void debug(String str) {
        create().debug(str);
    }

    /**
     * Logs a fatal error message via a new customized Logger instance.
     */
    public void fatal(String str) {
        create().fatal(str);
    }

    /**
     * Logs a fatal error message that includes a stack trace via a new customized Logger instance.
     */
    public void fatal(String str, Throwable throwable) {
        create().fatal(str, throwable);
    }

    /**
     * Logs a trace message via a new customized Logger instance.
     */
    public void trace(String str) {
        create().trace(str);
    }

    /**
     * Logs a trace message that includes a stack trace via a new customized Logger instance.
     */
    public void trace(String str, Throwable throwable) {
        create().trace(str, throwable);
    }

    /**
     * Logs a Throwable as an error message.
     */
    public void catching(Throwable throwable) {
        create().catching(throwable);
    }

    /**
     * Creates and returns a Logger instance based on the class it is called from.
     * @return a Logger instance.
     */
    public Logger create() {
        String callerClassName = Classes.callerClassSimpleName();
        if (callerClassName == null || callerClassName.isEmpty())
            return LogManager.getLogger();
        return crimson.getLoggingHelper().create(callerClassName);
    }

}