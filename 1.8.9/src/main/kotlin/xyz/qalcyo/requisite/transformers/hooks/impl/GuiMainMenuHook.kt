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

package xyz.qalcyo.requisite.transformers.hooks.impl

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.Window
import gg.essential.elementa.dsl.*
import gg.essential.universal.UDesktop
import gg.essential.universal.UMatrixStack
import net.minecraft.client.gui.GuiMainMenu
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.transformers.hooks.MixinHook
import xyz.qalcyo.requisite.gui.components.InteractableText
import java.net.URI

class GuiMainMenuHook(instance: GuiMainMenu) : MixinHook<GuiMainMenu>(instance) {

    var window = Window(ElementaVersion.V1, 60)
        private set

    init {
        val brandingText = InteractableText("${Requisite.getInstance().name()} v${Requisite.getInstance().version()}", true, InteractableText.Alignment.LEFT, {
            UDesktop.browse(URI.create("https://discord.gg/BJzuuc398G"))
        }).constrain {
            x = 2.pixels()
            y = 2.pixels()
        } childOf window
    }

    fun draw(mouseX: Int, mouseY: Int, partialTicks: Float) =
        window.draw(UMatrixStack.Compat.get())

    fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) =
        window.mouseClick(mouseX.toDouble(), mouseY.toDouble(), mouseButton)

}
