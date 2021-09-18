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

package xyz.qalcyo.requisite.core.keybinds

import xyz.qalcyo.mango.Lists
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.events.KeyInputEvent

class KeyBindRegistry(
    requisite: IRequisite
) {

    private var configurations: KeyBindConfigurations
    private val keyBinds: MutableList<KeyBind>
    fun register(keyBind: KeyBind) {
        configurations.load(keyBind)
        configurations.save(keyBind)
        keyBinds.add(keyBind)
    }

    fun unregister(name: String, category: String) {
        keyBinds.stream().filter {
                keyBind: KeyBind -> keyBind.name == name && keyBind.category == category
        }.findFirst().ifPresent {
                o: KeyBind -> keyBinds.remove(o)
        }
    }

    fun unregister(keyBind: KeyBind) {
        unregister(keyBind.name, keyBind.category)
    }

    private fun onKeyInput(event: KeyInputEvent) {
        for (keyBind in keyBinds) {
            if (configurations.isAvailable(keyBind) && keyBind.key == event.keyCode) {
                keyBind.handle(if (event.down) KeyBindState.PRESS else KeyBindState.RELEASE)
            }
        }
    }

    init {
        requisite.configurationManager.addConfigurable(KeyBindConfigurations().also { configurations = it })
        keyBinds = Lists.newArrayList()
        requisite.eventBus.register(KeyInputEvent::class.java) { event: KeyInputEvent ->
            onKeyInput(event)
        }
    }
}