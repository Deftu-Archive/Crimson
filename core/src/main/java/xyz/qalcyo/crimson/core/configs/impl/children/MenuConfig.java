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

package xyz.qalcyo.crimson.core.configs.impl.children;

import xyz.qalcyo.crimson.core.configs.IConfigChild;
import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;

import java.util.ArrayList;
import java.util.List;

public class MenuConfig implements IConfigChild {

    private Configuration configuration;
    private final List<IConfigChild> children = new ArrayList<>();

    private Subconfiguration self;

    private boolean optionsButton;
    private boolean keyBindsButton;

    public String getName() {
        return "menus";
    }

    public void initialize(Configuration configuration, Subconfiguration self) {
        this.configuration = configuration;
        this.self = self;

        if (!self.hasKey("options_button"))
            self.add("options_button", true).getParent().getAsConfiguration().save();
        optionsButton = self.getAsBoolean("options_button");
        if (!self.hasKey("keybinds_button"))
            self.add("keybinds_button", true).getParent().getAsConfiguration().save();
        keyBindsButton = self.getAsBoolean("keybinds_button");
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<IConfigChild> getChildren() {
        return children;
    }

    public boolean isOptionsButton() {
        return optionsButton;
    }

    public void setOptionsButton(boolean optionsButton) {
        this.optionsButton = optionsButton;
        self.add("options_button", optionsButton).getParent().getAsConfiguration().save();
    }

    public boolean isKeyBindsButton() {
        return keyBindsButton;
    }

    public void setKeyBindsButton(boolean keyBindsButton) {
        this.keyBindsButton = keyBindsButton;
        self.add("keybinds_button", keyBindsButton).getParent().getAsConfiguration().save();
    }

}