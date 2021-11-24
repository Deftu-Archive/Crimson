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

package xyz.qalcyo.crimson.core.gui.factory

import xyz.qalcyo.crimson.core.gui.components.Button
import xyz.qalcyo.crimson.core.gui.components.Clock
import xyz.qalcyo.crimson.core.gui.components.ConfirmationMenu
import xyz.qalcyo.crimson.core.gui.components.builders.ButtonBuilder
import xyz.qalcyo.crimson.core.gui.components.builders.ClockBuilder
import xyz.qalcyo.crimson.core.gui.components.builders.ConfirmationMenuBuilder

/**
 * A class which provides an easy way to build Crimson GUI Components.
 */
interface IComponentFactory {
    /**
     * Builds a Button component.
     */
    fun build(builder: ButtonBuilder) = Button(builder)
    /**
     * Builds a ConfirmationMenu component.
     */
    fun build(builder: ConfirmationMenuBuilder) = ConfirmationMenu(builder)
    /**
     * Builds a Clock component.
     */
    fun build(builder: ClockBuilder) = Clock(builder)
}