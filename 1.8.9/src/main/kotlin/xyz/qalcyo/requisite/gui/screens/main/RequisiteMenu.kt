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

package xyz.qalcyo.requisite.gui.screens.main

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.*
import gg.essential.universal.GuiScale
import gg.essential.universal.UKeyboard
import gg.essential.universal.UMatrixStack
import gg.essential.universal.UScreen
import net.minecraft.client.Minecraft
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.gui.screens.main.IRequisiteMenu
import xyz.qalcyo.requisite.core.gui.screens.main.RequisiteMenuPage
import xyz.qalcyo.requisite.core.gui.screens.main.impl.RequisiteControlsPage
import xyz.qalcyo.requisite.core.gui.screens.main.impl.RequisiteMainPage
import xyz.qalcyo.requisite.core.gui.screens.main.impl.RequisiteNetworkingPage
import xyz.qalcyo.requisite.core.integration.mods.IMod
import xyz.qalcyo.requisite.core.integration.mods.IModConfigurationMenu

class RequisiteMenu(
    pageIndex: Int = 0
) : UScreen(
    restoreCurrentGuiOnClose = true,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
), IRequisiteMenu, IModConfigurationMenu {

    override val window: Window = Window(ElementaVersion.V1)

    override val pages: Array<RequisiteMenuPage> = arrayOf(
        RequisiteMainPage(),
        RequisiteControlsPage(),
        RequisiteNetworkingPage()
    )

    override var page: RequisiteMenuPage = pages[pageIndex]
    override val title: UIText = UIText(page.title)
    override val content: UIContainer = UIContainer()

    init {
        window.onKeyType { typedChar, keyCode ->
            super.onKeyPressed(keyCode, typedChar, UKeyboard.getModifiers())
        }
    }

    override fun initScreen(width: Int, height: Int) {
        window.onWindowResize()
        initialize()
        super.initScreen(width, height)
    }

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }

    override fun onDrawScreen(matrixStack: UMatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.onDrawScreen(matrixStack, mouseX, mouseY, partialTicks)
        super.onDrawBackground(matrixStack, 0)
        window.draw(matrixStack)
    }

    override fun onMouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int) {
        super.onMouseClicked(mouseX, mouseY, mouseButton)
        window.mouseClick(mouseX, mouseY, mouseButton)
    }

    override fun onMouseReleased(mouseX: Double, mouseY: Double, state: Int) {
        super.onMouseReleased(mouseX, mouseY, state)
        window.mouseRelease()
    }

    override fun onMouseScrolled(delta: Double) {
        super.onMouseScrolled(delta)
        window.mouseScroll(delta.coerceIn(-1.0, 1.0))
    }

    override fun onKeyPressed(keyCode: Int, typedChar: Char, modifiers: UKeyboard.Modifiers?) {
        if (!page.keyTyped(typedChar, keyCode)) {
            window.keyType(typedChar, keyCode)
        }
    }

    override fun doesGuiPauseGame(): Boolean = false

    override fun open() = Requisite.getInstance().guiHelper.open(this)
    override fun getMod(): IMod = Requisite.getInstance()

}
