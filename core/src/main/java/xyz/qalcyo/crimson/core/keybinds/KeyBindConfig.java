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

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.crimson.core.configs.IConfigChild;
import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all configuration for Crimson's KeyBind API.
 */
class KeyBindConfig implements IConfigChild {

    /* Configuration manager. */

    private Configuration configuration;
    private List<IConfigChild> children = new ArrayList<>();

    private Subconfiguration self;

    public String getName() {
        return "keybinds";
    }

    /**
     * Initializes Crimson's KeyBind API to ensure that everything works properly.
     *
     * @param configuration The main Crimson configuration object.
     */
    public void initialize(Configuration configuration, Subconfiguration self) {
        this.configuration = configuration;
        this.self = self;
    }

    /* KeyBind logic. */

    /**
     * Saves a {@link KeyBind} to the subconfiguration.
     *
     * @param keyBind The {@link KeyBind} to save.
     */
    public void save(KeyBind keyBind) {
        if (!self.hasKey(keyBind.getCategory()))
            self.createSubconfiguration(keyBind.getCategory()).getParent().getAsConfiguration().save();
        Subconfiguration category = self.getSubconfiguration(keyBind.getCategory());
        if (!category.hasKey(keyBind.getName()))
            category.add(keyBind.getName(), keyBind.getKey()).getParent().getAsConfiguration().save();
    }

    /**
     * Loads a {@link KeyBind}'s key code.
     *
     * @param keyBind The {@link KeyBind} to load.
     */
    public void load(KeyBind keyBind) {
        int code = getKeyCode(keyBind);
        if (code != -1) {
            keyBind.setKey(code);
        }
    }

    /**
     * Fetches and returns a {@link KeyBind}'s key code saved from the KeyBind configurations.
     *
     * @param keyBind The {@link KeyBind} to fetch.
     * @return The {@link KeyBind}'s code.
     */
    public int getKeyCode(KeyBind keyBind) {
        if (self.hasKey(keyBind.getCategory())) {
            Subconfiguration category = self.getSubconfiguration(keyBind.getCategory());
            if (category.hasKey(keyBind.getName())) {
                JsonElement key = category.get(keyBind.getName());
                return key.isInt() ? key.getAsInt() : key.isDouble() ? (int) key.getAsDouble() : (int) key.getAsFloat();
            }
        }

        return -1;
    }

    /**
     * Checks if a {@link KeyBind} is available for use.
     *
     * @param keyBind The {@link KeyBind} to check for.
     * @return If the {@link KeyBind} is available for use.
     */
    public boolean isAvailable(KeyBind keyBind) {
        return getKeyCode(keyBind) != -1;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<IConfigChild> getChildren() {
        return children;
    }
}