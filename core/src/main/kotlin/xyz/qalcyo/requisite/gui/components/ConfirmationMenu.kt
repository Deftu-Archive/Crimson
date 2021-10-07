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

package xyz.qalcyo.requisite.gui.components

import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import gg.essential.elementa.utils.withAlpha
import xyz.qalcyo.requisite.core.RequisitePalette
import xyz.qalcyo.requisite.gui.components.builders.*

/**
 * An Elementa UIContainer meant for users to choose between a Yes or No question, and
 * for something to happen based on the decision.
 */
class ConfirmationMenu(
    private val builder: ConfirmationMenuBuilder
) : UIContainer() {
    init {
        var contentColor = RequisitePalette.getComponentContent().asColor()
        if (builder.backgroundOpacity != null)
            contentColor = contentColor.withAlpha(builder.backgroundOpacity!!)
        val content = UIBlock(contentColor).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
            width = RelativeConstraint()
            height = RelativeConstraint()
        } childOf this effect OutlineEffect(RequisitePalette.getMain().asColor(), 1f)

        val declineButton = Button({
            builder.decline.invoke(this@ConfirmationMenu)
        }, "Decline").constrain {
            x = CenterConstraint() + 52.pixels()
            y = 6.pixels(true)
            width = 100.pixels()
            height = 20.pixels()
        } childOf content

        val acceptButton = Button({
            builder.accept.invoke(this@ConfirmationMenu)
        }, "Accept").constrain {
            x = CenterConstraint() - 52.pixels()
            y = 6.pixels(true)
            width = 100.pixels()
            height = 20.pixels()
        } childOf content

        val text = UIText(builder.text).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf this
    }
}
