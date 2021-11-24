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

package xyz.qalcyo.crimson.core.gui.components;

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.crimson.core.data.ColourRGB
import xyz.qalcyo.crimson.core.gui.components.builders.ClockBuilder
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * An Elementa UIComponent that displays a virtual clock.
 */
class Clock(
    private val builder: ClockBuilder
) : UIComponent() {

    constructor(colour: ColourRGB, timeZone: String = "GMT", format: String = "mm:ss", prefix: String = "", suffix: String = "", textShadow: Boolean = false, textShadowColour: ColourRGB? = null) : this(ClockBuilder(colour, timeZone, format, prefix, suffix, textShadow, textShadowColour))

    val text: UIText = UIText("${builder.prefix}${getCurrentTime()}${builder.suffix}", builder.textShadow, if (builder.textShadowColour == null) null else builder.textShadowColour!!.asColor()).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = RelativeConstraint()
        height = RelativeConstraint()
    } childOf this

    init {
        text.setColor(builder.colour.asColor())
    }

    override fun draw() {
        beforeDraw()
        text.setText("${builder.prefix}${getCurrentTime()}${builder.suffix}")
        super.draw()
    }

    private fun getCurrentTime(): String {
        val timeZone = TimeZone.getTimeZone(builder.timeZone)
        val calendar = Calendar.getInstance(timeZone)
        return OffsetDateTime.ofInstant(calendar.toInstant(), timeZone.toZoneId()).format(DateTimeFormatter.ofPattern(builder.format))
    }

}