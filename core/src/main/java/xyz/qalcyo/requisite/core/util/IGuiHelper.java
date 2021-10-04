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

package xyz.qalcyo.requisite.core.util;

public interface IGuiHelper<T> {
    /**
     * Opens the GUI given synchronously, avoiding mouse glitches.
     */
    void open(T gui);

    /**
     * Forces a GUI to open synchronously, ignoring type safety.
     */
    default void forceOpen(Object gui) {
        open((T) gui);
    }

    /**
     * @return Whether a menu is currently open or not.
     */
    boolean isGuiPresent();

    /**
     * @return The current GUI waiting to be opened.
     */
    T getAwaiting();
}