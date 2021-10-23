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

import org.lwjgl.input.Keyboard;
import xyz.qalcyo.eventbus.EventPriority;
import xyz.qalcyo.eventbus.SubscribeEvent;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.events.KeyInputEvent;
import xyz.qalcyo.requisite.core.events.RenderTickEvent;

import java.util.List;

/**
 * Core of the Requisite KeyBind API.
 */
public class KeyBindRegistry {

    private final RequisiteAPI requisite;

    private final KeyBindConfigurations configurations;
    private final List<KeyBind> keyBinds;

    public KeyBindRegistry(RequisiteAPI requisite) {
        this.requisite = requisite;

        requisite.getConfigurationManager().addConfigurable(this.configurations = new KeyBindConfigurations());
        this.keyBinds = Lists.newArrayList();

        requisite.getEventBus().register(this);
    }

    /**
     * Registers a new {@link KeyBind} with Requisite's KeyBind API.
     *
     * @param keyBind The new {@link KeyBind} to register.
     */
    public void register(KeyBind keyBind) {
        configurations.load(keyBind);
        configurations.save(keyBind);
        keyBinds.add(keyBind);
    }

    /**
     * Unregisters a {@link KeyBind} from Requisite's KeyBind API.
     *
     * @param name The name of the {@link KeyBind} to unregister.
     * @param category The category this keybind falls under.
     */
    public void unregister(String name, String category) {
        keyBinds.stream().filter(keyBind -> keyBind.getName().equals(name) && keyBind.getCategory().equals(category)).findFirst().ifPresent(keyBinds::remove);
    }

    /**
     * Unregisters a {@link KeyBind} from Requisite's KeyBind API.
     *
     * @param keyBind The {@link KeyBind} to unregister.
     */
    public void unregister(KeyBind keyBind) {
        unregister(keyBind.getName(), keyBind.getCategory());
    }

    /**
     * Parses key input to Requisite's KeyBind API.
     *
     * @param event The event called on key input.
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    private void onKeyInput(KeyInputEvent event) {
        if (!requisite.getGuiHelper().isGuiPresent()) {
            for (KeyBind keyBind : keyBinds) {
                if (configurations.isAvailable(keyBind) && keyBind.getKey() == event.keyCode) {
                    keyBind.handle(event.down ? KeyBindState.PRESS : KeyBindState.RELEASE);
                }
            }
        }
    }

}