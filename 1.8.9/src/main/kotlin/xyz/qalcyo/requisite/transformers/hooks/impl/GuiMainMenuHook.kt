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

import gg.essential.elementa.dsl.*
import gg.essential.universal.UScreen
import net.minecraft.client.gui.GuiMainMenu
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.transformers.hooks.GuiMixinHook
import xyz.qalcyo.requisite.gui.components.InteractableText
import xyz.qalcyo.requisite.gui.screens.CreditsMenu

class GuiMainMenuHook(
    instance: GuiMainMenu
) : GuiMixinHook<GuiMainMenu>(
    instance
) {
    init {
        val brandingText = InteractableText("${Requisite.getInstance().name()} v${Requisite.getInstance().version()}", true, InteractableText.Alignment.RIGHT, {
            UScreen.displayScreen(CreditsMenu())
        }).constrain {
            x = 2.pixels(true)
            y = 11.pixels(true)
        } childOf window
    }
}