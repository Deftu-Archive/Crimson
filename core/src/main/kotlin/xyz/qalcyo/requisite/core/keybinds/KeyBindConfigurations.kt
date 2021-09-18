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

import xyz.qalcyo.requisite.core.files.ConfigurationManager
import xyz.qalcyo.requisite.core.files.IConfigurable
import xyz.qalcyo.simpleconfig.Configuration
import xyz.qalcyo.simpleconfig.Subconfiguration

internal class KeyBindConfigurations : IConfigurable {

    /* Configuration manager. */
    private var configuration: Subconfiguration? = null
    override fun initialize(configuration: Configuration) {
        if (!configuration.hasKey("keybinds")) configuration.createSubconfiguration("keybinds").save()
        this.configuration = configuration.getSubconfiguration("keybinds")
    }

    override fun save(configurationManager: ConfigurationManager) {}
    override fun load(configurationManager: ConfigurationManager) {}
    override fun configuration(): Subconfiguration {
        return configuration!!
    }

    /* KeyBind logic. */

    fun save(keyBind: KeyBind) {
        val configuration = configuration()
        if (!configuration.hasKey(keyBind.category)) configuration.createSubconfiguration(keyBind.category).parent.asConfiguration.save()
        val category = configuration.getSubconfiguration(keyBind.category)
        category.add(keyBind.name, keyBind.key).parent.asConfiguration.save()
    }

    fun load(keyBind: KeyBind) {
        val code = getKeyCode(keyBind)
        if (code != -1) {
            keyBind.key = code
        }
    }

    fun getKeyCode(keyBind: KeyBind): Int {
        val configuration = configuration()
        if (configuration.hasKey(keyBind.category)) {
            val category = configuration.getSubconfiguration(keyBind.category)
            if (category.hasKey(keyBind.name)) {
                val key = category[keyBind.name]
                return if (key.isInt) key.asInt else if (key.isDouble) key.asDouble.toInt() else key.asFloat.toInt()
            }
        }
        return -1
    }

    fun isAvailable(keyBind: KeyBind): Boolean =
        getKeyCode(keyBind) != -1

}