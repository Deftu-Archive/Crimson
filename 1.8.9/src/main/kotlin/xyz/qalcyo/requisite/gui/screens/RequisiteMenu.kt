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
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.Window
import gg.essential.elementa.components.inspector.Inspector
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import gg.essential.universal.UMatrixStack
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.integration.mods.IMod
import xyz.qalcyo.requisite.core.integration.mods.IModConfigurationMenu
import xyz.qalcyo.requisite.gui.components.Button
import xyz.qalcyo.requisite.gui.components.InteractableText
import xyz.qalcyo.requisite.gui.components.builders.ButtonBuilder

/**
 * The Requisite Menu GUI, which allows the user to easily access Requisite (and mods that are integrated into it) settings.
 */
class RequisiteMenu :
    IModConfigurationMenu,
    WindowScreen(
        version = ElementaVersion.V1,
        restoreCurrentGuiOnClose = true
    ) {

    init {
        /* Branding. */
        val brandingContainer = UIContainer().constrain {
            x = 2.pixels()
            y = 2.pixels(true)
            width = ChildBasedSizeConstraint()
            height = ChildBasedSizeConstraint()
        } childOf window

        val branding = InteractableText(Requisite.getInstance().name() + " v" + Requisite.getInstance().version(), true, InteractableText.Alignment.LEFT, {
            open()
        }).constrain {
            x = 2.pixels()
            y = 2.pixels(true)
        } childOf brandingContainer


        /* Buttons. */
        val buttonContainer = UIContainer().constrain {
            x = CenterConstraint()
            y = CenterConstraint()
            width = ChildBasedSizeConstraint()
            height = ChildBasedSizeConstraint()
        } childOf window

        val close = ButtonBuilder({
            restorePreviousScreen()
        }, "Close").build(Requisite.getInstance().componentFactory).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
            width = Button.DEFAULT_WIDTH_PIXELS
            height = Button.DEFAULT_HEIGHT_PIXELS
        } childOf buttonContainer

        val cosmetics = ButtonBuilder({
            displayScreen(CosmeticMenu())
        }, "Cosmetics").build(Requisite.getInstance().componentFactory).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(2f, true)
            width = Button.DEFAULT_WIDTH_PIXELS
            height = Button.DEFAULT_HEIGHT_PIXELS
        } childOf buttonContainer
    }

    override fun open() = Requisite.getInstance().openMenu()
    override fun getMod(): IMod = Requisite.getInstance()

}
