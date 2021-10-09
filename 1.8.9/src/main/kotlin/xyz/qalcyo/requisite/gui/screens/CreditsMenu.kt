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

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.ScrollComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.RequisitePalette
import xyz.qalcyo.requisite.gui.components.Button
import xyz.qalcyo.requisite.gui.components.builders.ButtonBuilder

class CreditsMenu : WindowScreen(
    ElementaVersion.V1,
    restoreCurrentGuiOnClose = true
) {
    init {
        val scrollable = ScrollComponent().constrain {
            x = 0.pixels()
            y = 0.pixels()
            width = RelativeConstraint()
            height = 50.pixels(true)
        } childOf window
        scrollable.setVerticalScrollBarComponent(ScrollComponent.DefaultScrollBar(false), true)
        scrollable.setEmptyText("I'm empty :(")

        val divider = UIBlock(RequisitePalette.getComponentContent().asColor()).constrain {
            x = 0.pixels()
            y = 50.pixels(true)
            width = RelativeConstraint()
            height = 1.pixel()
        } childOf window

        val backButton = ButtonBuilder({
            restorePreviousScreen()
        }, "Back").build(Requisite.getInstance().componentFactory).constrain {
            x = CenterConstraint()
            y = 25.pixels(true)
            width = Button.DEFAULT_WIDTH_SMALL_PIXELS
            height = Button.DEFAULT_HEIGHT_PIXELS
        } childOf window
    }
}