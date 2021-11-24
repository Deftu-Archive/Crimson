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

import xyz.qalcyo.eventbus.EventPriority;
import xyz.qalcyo.eventbus.SubscribeEvent;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.crimson.core.CrimsonAPI;
import xyz.qalcyo.crimson.core.events.KeyInputEvent;

import java.util.List;

/**
 * Core of the Crimson KeyBind API.
 */
public class KeyBindRegistry {

    private final CrimsonAPI crimson;

    private final KeyBindConfig configurations;
    private final List<KeyBind> keyBinds;

    public KeyBindRegistry(CrimsonAPI crimson) {
        this.crimson = crimson;

        crimson.getConfigManager().getAverage().addChild(this.configurations = new KeyBindConfig());
        this.keyBinds = Lists.newArrayList();

        crimson.getEventBus().register(this);
    }

    /**
     * Registers a new {@link KeyBind} with Crimson's KeyBind API.
     *
     * @param keyBind The new {@link KeyBind} to register.
     */
    public void register(KeyBind keyBind) {
        configurations.load(keyBind);
        configurations.save(keyBind);
        keyBinds.add(keyBind);
    }

    /**
     * Unregisters a {@link KeyBind} from Crimson's KeyBind API.
     *
     * @param name The name of the {@link KeyBind} to unregister.
     * @param category The category this keybind falls under.
     */
    public void unregister(String name, String category) {
        keyBinds.stream().filter(keyBind -> keyBind.getName().equals(name) && keyBind.getCategory().equals(category)).findFirst().ifPresent(keyBinds::remove);
    }

    /**
     * Unregisters a {@link KeyBind} from Crimson's KeyBind API.
     *
     * @param keyBind The {@link KeyBind} to unregister.
     */
    public void unregister(KeyBind keyBind) {
        unregister(keyBind.getName(), keyBind.getCategory());
    }

    /**
     * Saves a {@link KeyBind} to it's configuration file.
     *
     * @param keyBind The {@link KeyBind} to save.
     */
    public void save(KeyBind keyBind) {
        configurations.save(keyBind);
    }

    /**
     * Parses key input to Crimson's KeyBind API.
     *
     * @param event The event called on key input.
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    private void onKeyInput(KeyInputEvent event) {
        if (!crimson.getGuiHelper().isGuiPresent()) {
            for (KeyBind keyBind : keyBinds) {
                if (configurations.isAvailable(keyBind) && keyBind.getKey() == event.keyCode) {
                    keyBind.handle(event.down ? KeyBindState.PRESS : KeyBindState.RELEASE);
                }
            }
        }
    }

    public List<KeyBind> getKeyBinds() {
        return keyBinds;
    }

}