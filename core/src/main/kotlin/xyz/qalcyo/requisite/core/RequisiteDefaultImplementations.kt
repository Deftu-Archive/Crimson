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
package xyz.qalcyo.requisite.core

import org.apache.logging.log4j.LogManager
import xyz.qalcyo.requisite.core.files.configs.PrivacyConfigurations
import xyz.qalcyo.requisite.core.util.*
import xyz.qalcyo.simpleeventbus.SimpleEventBus

internal object RequisiteDefaultImplementations {
    val javaArguments = RequisiteJavaArguments()
    val logger = LogManager.getLogger("Requisite")
    val eventBus = SimpleEventBus()

    val privacyConfigurations = PrivacyConfigurations()

    val colourHelper = ColourHelper()
    val loggingHelper = LoggingHelper()
    val clipboardHelper = ClipboardHelper()
    val dateHelper = DateHelper()
    val easingHelper = EasingHelper()
    val mathHelper = MathHelper()
    val multithreading = Multithreading()
    val reflectionHelper = ReflectionHelper()
    val romanNumerals = RomanNumeral()
    val stringHelper = StringHelper()
    val mojangApi = MojangAPI()
}