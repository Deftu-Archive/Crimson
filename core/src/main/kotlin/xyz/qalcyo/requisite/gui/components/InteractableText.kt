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

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import java.awt.Color

/**
 * An Elementa UIText which provides more customization towards
 * interaction.
 */
class InteractableText @JvmOverloads constructor(
    text: String = "",
    hoverUnderline: Boolean = true,
    hoverUnlineAlignment: Alignment = Alignment.CENTER,
    action: InteractableText.() -> Unit = {},

    color: Color = Color.WHITE,

    shadow: Boolean = true,
    shadowColour: Color? = null
) : UIText(
    text,
    shadow,
    shadowColour
) {

    private val underline = UIBlock(color).constrain {
        x = if (hoverUnlineAlignment == Alignment.CENTER) CenterConstraint() else 0.pixels(hoverUnlineAlignment == Alignment.RIGHT)
        y = 0.pixels(true)
        width = 0.pixels()
        height = 1.pixel()
    } childOf this

    init {
        setColor(color)
        if (!hoverUnderline) {
            underline.hide(true)
        }

        onMouseEnter {
            if (hoverUnderline) {
                underline.animate {
                    setWidthAnimation(Animations.OUT_EXP, 1f, this@InteractableText.getWidth().pixels())
                }
            }
        }.onMouseLeave {
            if (hoverUnderline) {
                underline.animate {
                    setWidthAnimation(Animations.OUT_EXP, 1f, 0.pixels())
                }
            }
        }.onMouseClick {
            action.invoke(this@InteractableText)
        }
    }

    enum class Alignment {
        LEFT,
        CENTER,
        RIGHT
    }

}
