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

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.requisite.core.files.ConfigurationManager;
import xyz.qalcyo.requisite.core.files.IConfigurable;
import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;

class KeyBindConfigurations implements IConfigurable {

    /* Configuration manager. */

    private Subconfiguration configuration;

    public void initialize(Configuration configuration) {
        if (!configuration.hasKey("keybinds"))
            configuration.createSubconfiguration("keybinds").save();
        this.configuration = configuration.getSubconfiguration("keybinds");
    }

    public void save(ConfigurationManager configurationManager) {}

    public void load(ConfigurationManager configurationManager) {}

    public Subconfiguration configuration() {
        return configuration;
    }

    /* KeyBind logic. */

    public void save(KeyBind keyBind) {
        Subconfiguration configuration = configuration();

        if (!configuration.hasKey(keyBind.getCategory()))
            configuration.createSubconfiguration(keyBind.getCategory()).getParent().getAsConfiguration().save();
        Subconfiguration category = configuration.getSubconfiguration(keyBind.getCategory());
        category.add(keyBind.getName(), keyBind.getKey()).getParent().getAsConfiguration().save();
    }

    public void load(KeyBind keyBind) {
        int code = getKeyCode(keyBind);
        if (code != -1) {
            keyBind.setKey(code);
        }
    }

    public int getKeyCode(KeyBind keyBind) {
        Subconfiguration configuration = configuration();

        if (configuration.hasKey(keyBind.getCategory())) {
            Subconfiguration category = configuration.getSubconfiguration(keyBind.getCategory());
            if (category.hasKey(keyBind.getName())) {
                JsonElement key = category.get(keyBind.getName());
                return key.isInt() ? key.getAsInt() : key.isDouble() ? (int) key.getAsDouble() : (int) key.getAsFloat();
            }
        }

        return -1;
    }

    public boolean isAvailable(KeyBind keyBind) {
        return getKeyCode(keyBind) != -1;
    }

}