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

package xyz.qalcyo.crimson.core.gui.components.builders

import gg.essential.elementa.UIComponent
import xyz.qalcyo.mango.data.Colour
import xyz.qalcyo.crimson.core.data.ColourRGB
import xyz.qalcyo.crimson.core.gui.factory.IComponentFactory
import java.util.*

/**
 * A builder which makes it easier to make Clock instances.
 */
class ClockBuilder(
    var colour: ColourRGB = ColourRGB(Colour.WHITE),

    var timeZone: String = Calendar.getInstance().timeZone.id,
    var format: String = "mm:ss",

    val prefix: String = "",
    val suffix: String = "",

    var textShadow: Boolean = false,
    var textShadowColour: ColourRGB? = null
) {
    fun build(factory: IComponentFactory): UIComponent = factory.build(this)
}
