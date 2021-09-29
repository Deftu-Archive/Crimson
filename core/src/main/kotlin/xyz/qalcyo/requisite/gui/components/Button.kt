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

import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import xyz.qalcyo.requisite.core.*
import xyz.qalcyo.requisite.gui.components.builders.*
import java.awt.Color

class Button(
    private val builder: ButtonBuilder
) : UIContainer() {
    
    constructor(action: Button.() -> Unit, text: String) : this(ButtonBuilder(action, text))

    private val border = UIBlock(Color(0, 0, 0, 0)).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = RelativeConstraint()
        height = RelativeConstraint()
    } childOf this effect OutlineEffect(Color(0, 0, 0, 0), 1f)
    private val content = UIBlock(RequisitePalette.getButtonPallete().content.asColor()).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = RelativeConstraint()
        height = RelativeConstraint()
    } childOf this
    private val text = UIText(builder.text).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    } childOf content

    init {
        onMouseEnter {
            border.animate {
                (border.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, RequisitePalette.getButtonPallete().border.asColor())
            }
        }.onMouseLeave {
            border.animate {
                (border.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, Color(0, 0, 0, 0))
            }
        }.onMouseClick {
            builder.action.invoke(this as Button)
        }
    }

    fun setText(input: String) = text.setText(input)

}
