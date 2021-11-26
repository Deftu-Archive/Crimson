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

package xyz.qalcyo.crimson.gui.screens.onboarding

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.Window
import gg.essential.universal.GuiScale
import gg.essential.universal.UKeyboard
import gg.essential.universal.UMatrixStack
import gg.essential.universal.UScreen
import net.minecraft.client.Minecraft
import xyz.qalcyo.crimson.core.gui.screens.onboarding.ICrimsonOnboardingMenu
import xyz.qalcyo.crimson.core.gui.screens.onboarding.CrimsonOnboardingSlideBase
import xyz.qalcyo.crimson.core.gui.screens.onboarding.SlideBox

class CrimsonOnboardingMenu : UScreen(
    restoreCurrentGuiOnClose = true,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
), ICrimsonOnboardingMenu {

    override val window = Window(ElementaVersion.V1)
    override var initialized: Boolean = false

    override var slide: CrimsonOnboardingSlideBase = CrimsonOnboardingSlideBase.slides[0]
    override val content = UIContainer()

    override lateinit var slideBox: SlideBox
    override val slideBoxes: MutableList<SlideBox> = mutableListOf()

    /* Screen logic. */

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
        window.keyType(typedChar, keyCode)
    }

    override fun doesGuiPauseGame(): Boolean = false

}