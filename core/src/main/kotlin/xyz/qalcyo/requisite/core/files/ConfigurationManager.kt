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
package xyz.qalcyo.requisite.core.files

import xyz.qalcyo.simpleconfig.Configuration
import java.io.File

class ConfigurationManager(name: String?, path: File?) : Thread() {

    val configuration: Configuration
    val configurables: MutableList<IConfigurable>

    fun update() {
        for (configurable in configurables) {
            configurable.initialize(configuration)
            configurable.load(this)
            configurable.save(this)
        }
    }

    fun save() =
        configuration.save()

    fun addConfigurable(configurable: IConfigurable): ConfigurationManager {
        configurables.add(configurable)
        configurable.initialize(configuration)
        configurable.load(this)
        configurable.save(this)
        configuration.save()
        return this
    }

    init {
        configuration = Configuration(name, path)
        configurables = ArrayList()
    }

}