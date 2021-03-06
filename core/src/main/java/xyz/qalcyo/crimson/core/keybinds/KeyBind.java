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

package xyz.qalcyo.crimson.core.keybinds;

/**
 * Main class for Crimson's {@link KeyBind} API handling.
 * @see KeyBinds
 */
public abstract class KeyBind {

    private final String name, category;
    private int key;

    public KeyBind(String name, String category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
    }

    /**
     * Handles key presses with the state.
     *
     * @param state The state of the key during this input.
     */
    public abstract void handle(KeyBindState state);

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}