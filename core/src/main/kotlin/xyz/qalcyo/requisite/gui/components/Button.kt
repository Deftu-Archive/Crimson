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

import gg.essential.elementa.*
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.*
import gg.essential.elementa.utils.*
import xyz.qalcyo.requisite.core.*
import xyz.qalcyo.requisite.gui.components.builders.*
import java.awt.*
import java.util.concurrent.*

class Button(
    private val builder: ButtonBuilder
) : UIComponent() {

    val holder = UIContainer().constrain {
        width = builder.width.pixels()
        height = builder.height.pixels()
    } childOf this

    val box = UIBlock(Color.DARK_GRAY.withAlpha(219)).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = holder.constraints.width
        height = holder.constraints.height
    } childOf holder effect OutlineEffect(RequisitePalette.getMain().asColor(), 0.5f)

    init {
        if (!builder.text.isNullOrEmpty()) {
            val text = UIWrappedText(builder.text!!, builder.textShadow)
            if (builder.textShadow && builder.textShadowColour != null) {
                text.setShadowColor(builder.textShadowColour)
            }

            text.constrain {
                x = CenterConstraint()
                y = CenterConstraint()
                width = holder.constraints.width
                height = holder.constraints.height
            } childOf holder
        }

        if (builder.image != null) {
            val image = UIImage(CompletableFuture.supplyAsync { builder.image }).constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            } childOf holder
        }

        holder.onMouseClick {
            builder.action.invoke(this)
        }
    }

}
