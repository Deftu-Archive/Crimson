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

package xyz.qalcyo.requisite.gui.screens

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.mango.data.Colour
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.data.ColourRGB
import xyz.qalcyo.requisite.gui.components.InteractableText
import xyz.qalcyo.requisite.gui.components.builders.ButtonBuilder
import xyz.qalcyo.requisite.gui.components.builders.ClockBuilder
import xyz.qalcyo.requisite.gui.components.builders.ConfirmationMenuBuilder
import xyz.qalcyo.requisite.kotlin.dsl.*

class TestMenu : WindowScreen(drawDefaultBackground = false) {

    init {
        val text = InteractableText("Interact with me!", true, {
            Requisite.getInstance().chatHelper.send("Hello!")
        }).constrain {
            x = CenterConstraint()
            y = CenterConstraint() + 30.pixels()
            width = 100.pixels()
            height = 12.pixels()
        } childOf window
    }

}
