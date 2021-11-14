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

package xyz.qalcyo.requisite.core.keybinds;

import java.util.function.Consumer;

/**
 * Class that provides easy access to instances of the {@link KeyBind} class.
 * @see KeyBind
 */
public class KeyBinds {

    /**
     * Creates and returns a {@link KeyBind} instance from the parameters given.
     *
     * @return A {@link KeyBind} instance.
     */
    public static KeyBind from(String name, String category, int key, Runnable press, Runnable release) {
        return new KeyBind(name, category, key) {
            public void handle(KeyBindState state) {
                if (state == KeyBindState.PRESS) {
                    press.run();
                } else if (state == KeyBindState.RELEASE) {
                    release.run();
                }
            }
        };
    }

    /**
     * Creates and returns a {@link KeyBind} instance from the parameters given.
     *
     * @return A {@link KeyBind} instance.
     */
    public static KeyBind from(String name, String category, int key, Runnable press) {
        return from(name, category, key, press, () -> {});
    }

    /**
     * Creates and returns a {@link KeyBind} instance from the parameters given.
     *
     * @return A {@link KeyBind} instance.
     */
    public static KeyBind from(String name, String category, int key, Consumer<KeyBindState> executor) {
        return new KeyBind(name, category, key) {
            public void handle(KeyBindState state) {
                executor.accept(state);
            }
        };
    }

}