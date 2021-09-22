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

package xyz.qalcyo.requisite.util;

import xyz.qalcyo.requisite.core.util.IPositionHelper;
import xyz.qalcyo.requisite.data.ScreenPosition;

public class PositionHelper implements IPositionHelper {

    public ScreenPosition createDefaultPosition() {
        return ScreenPosition.fromRaw(30, 30);
    }

    public ScreenPosition createPosition(float x, float y) {
        return ScreenPosition.fromRaw(x, y);
    }

}