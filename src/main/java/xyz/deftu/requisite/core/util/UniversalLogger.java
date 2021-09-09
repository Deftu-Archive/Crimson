/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.deftu.requisite.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.deftu.requisite.core.IRequisite;

public class UniversalLogger {

    private final IRequisite requisite;

    public UniversalLogger(IRequisite requisite) {
        this.requisite = requisite;
    }

    public void info(String str) {
        create().info(str);
    }

    public void warn(String str) {
        create().warn(str);
    }

    public void error(String str) {
        create().error(str);
    }

    public void error(String str, Throwable throwable) {
        create().error(str, throwable);
    }

    public void debug(String str) {
        create().debug(str);
    }

    public void fatal(String str) {
        create().fatal(str);
    }

    public void fatal(String str, Throwable throwable) {
        create().fatal(str, throwable);
    }

    public void trace(String str) {
        create().trace(str);
    }

    public void trace(String str, Throwable throwable) {
        create().trace(str, throwable);
    }

    public void catching(Throwable throwable) {
        create().catching(throwable);
    }

    public Logger create() {
        String callerClassName = callerClassName();
        if (callerClassName == null || callerClassName.isEmpty())
            return LogManager.getLogger();
        return requisite.getLoggingHelper().create(callerClassName);
    }

    private String callerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stElements) {
            if (!element.getClassName().equals(getClass().getName()) && element.getClassName().indexOf("java.lang.Thread") != 0) {
                String className = element.getClassName();
                return className.substring(className.lastIndexOf(".") + 1);
            }
        }
        return null;
    }

}