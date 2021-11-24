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

package xyz.qalcyo.crimson.gui.components

import gg.essential.elementa.UIComponent
import gg.essential.elementa.dsl.*
import net.minecraft.client.gui.inventory.GuiInventory
import xyz.qalcyo.crimson.gui.components.builders.UIPlayerBuilder

open class UIPlayer(
    private val builder: UIPlayerBuilder
) : UIComponent() {

    private var dragging = false

    init {
        constrain {
            width = 60.pixels()
            height = 180.pixels()
        }

        onMouseClick {
            if (builder.rotatable) {
                dragging = true
            }
        }.onMouseRelease {
            dragging = false
        }
    }

    final override fun draw() {
        beforeDraw()

        GuiInventory.drawEntityOnScreen(
            getLeft().toInt(),
            getTop().toInt(),
            ((getWidth() * 2).coerceAtMost(getHeight()) * 0.525f).toInt(),
            0f,
            0f,
            builder.entity
        )

        super.draw()
    }

}
