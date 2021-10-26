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

import org.lwjgl.input.Keyboard;
import xyz.qalcyo.requisite.core.util.IKeyboardHelper;

public class KeyboardHelper implements IKeyboardHelper {

    public String getKeyName(int keyCode) {
        return Keyboard.getKeyName(keyCode);
    }

    public boolean isKeyDown(int keyCode) {
        return Keyboard.isKeyDown(keyCode);
    }

    public boolean isEscapeKey(int keyCode) {
        return keyCode == Keyboard.KEY_ESCAPE;
    }

    public boolean isCtrlKey(int keyCode) {
        return isWithin(keyCode,
                Keyboard.KEY_LCONTROL,
                Keyboard.KEY_RCONTROL);
    }

    public boolean isAltKey(int keyCode) {
        return isWithin(keyCode,
                Keyboard.KEY_LMETA,
                Keyboard.KEY_RMETA);
    }

    public boolean isEnterKey(int keyCode) {
        return keyCode == Keyboard.KEY_END;
    }

    public boolean isTabKey(int keyCode) {
        return keyCode == Keyboard.KEY_TAB;
    }

    public boolean isCapsLockKey(int keyCode) {
        return keyCode == Keyboard.KEY_CAPITAL;
    }

    public boolean isFunctionKey(int keyCode) {
        return isWithin(keyCode,
                Keyboard.KEY_F1,
                Keyboard.KEY_F2,
                Keyboard.KEY_F3,
                Keyboard.KEY_F4,
                Keyboard.KEY_F5,
                Keyboard.KEY_F6,
                Keyboard.KEY_F7,
                Keyboard.KEY_F8,
                Keyboard.KEY_F9,
                Keyboard.KEY_F10,
                Keyboard.KEY_F11,
                Keyboard.KEY_F12,
                Keyboard.KEY_F13,
                Keyboard.KEY_F14,
                Keyboard.KEY_F15,
                Keyboard.KEY_F16,
                Keyboard.KEY_F17,
                Keyboard.KEY_F18,
                Keyboard.KEY_F19);
    }

    public boolean isBackspaceKey(int keyCode) {
        return false;
    }

    private boolean isWithin(int value, int... values) {
        boolean val = false;
        for (int i : values) {
            if (value == i) {
                val = true;
                break;
            }
        }

        return val;
    }

}