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

import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.events.KeyInputEvent;

import java.util.List;

public class KeyBindRegistry {

    private final KeyBindConfigurations configurations;
    private final List<KeyBind> keyBinds;

    public KeyBindRegistry(IRequisite requisite) {
        requisite.getConfigurationManager().addConfigurable(this.configurations = new KeyBindConfigurations());
        this.keyBinds = Lists.newArrayList();

        requisite.getEventBus().register(KeyInputEvent.class, this::onKeyInput);
    }

    public void register(KeyBind keyBind) {
        configurations.load(keyBind);
        configurations.save(keyBind);
        keyBinds.add(keyBind);
    }

    public void unregister(String name, String category) {
        keyBinds.stream().filter(keyBind -> keyBind.getName().equals(name) && keyBind.getCategory().equals(category)).findFirst().ifPresent(keyBinds::remove);
    }

    public void unregister(KeyBind keyBind) {
        unregister(keyBind.getName(), keyBind.getCategory());
    }

    private void onKeyInput(KeyInputEvent event) {
        for (KeyBind keyBind : keyBinds) {
            if (configurations.isAvailable(keyBind) && keyBind.getKey() == event.keyCode) {
                keyBind.handle(event.down ? KeyBindState.PRESS : KeyBindState.RELEASE);
            }
        }
    }

}