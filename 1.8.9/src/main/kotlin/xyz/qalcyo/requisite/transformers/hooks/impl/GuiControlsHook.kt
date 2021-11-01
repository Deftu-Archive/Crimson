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
import net.minecraft.client.gui.GuiControls
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.gui.components.builders.ButtonBuilder
import xyz.qalcyo.requisite.core.transformers.hooks.GuiMixinHook

class GuiControlsHook(
    instance: GuiControls
) : GuiMixinHook<GuiControls>(
    instance
) {
    init {
        if (Requisite.getInstance().configManager.menu.isKeyBindsButton) {
            val keyBindsButton = ButtonBuilder({
                Requisite.getInstance().openRequisiteMenu(1)
            }, "R").build(Requisite.getInstance().componentFactory).constrain {
                x = 10.pixels()
                y = 6.pixels(true)
                width = 20.pixels()
                height = 20.pixels()
            } childOf window
        }
    }
}