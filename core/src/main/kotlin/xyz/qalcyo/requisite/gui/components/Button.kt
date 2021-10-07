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

/**
 * An Elementa UIContainer which represents and acts like a button.
 */
class Button(
    private val builder: ButtonBuilder
) : UIContainer() {

    constructor(action: Button.() -> Unit, text: String, toggled: Boolean = true) : this(ButtonBuilder(action, text, toggled))

    private val border = UIBlock(Color(0, 0, 0, 0)).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = RelativeConstraint()
        height = RelativeConstraint()
    } childOf this effect OutlineEffect(Color(0, 0, 0, 0), 1f)
    private val content = UIBlock(RequisitePalette.getComponentContent().asColor()).constrain {
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
        if (!builder.toggled) animateTextColour(DISABLED_COLOUR)
        onMouseEnter {
            border.animate {
                (border.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, RequisitePalette.getMain().asColor())
            }
        }.onMouseLeave {
            border.animate {
                (border.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, 1f, Color(0, 0, 0, 0))
            }
        }.onMouseClick {
            if (builder.toggled) {
                builder.action.invoke(this@Button)
            }
        }
    }

    fun setText(input: String) = text.setText(input)
    fun setTextColour(colour: Color, animate: Boolean = true) = if (animate) animateTextColour(colour) else text.setColor(colour)
    fun setToggled(toggled: Boolean) = animateTextColour(if (toggled) Color.WHITE else DISABLED_COLOUR).also { builder.toggled = toggled }
    fun toggle(): Boolean {
        setToggled(!builder.toggled)
        return builder.toggled
    }

    @JvmOverloads fun animateTextColour(colour: Color, time: Float = 1f) = animateTextColour(ConstantColorConstraint(colour), time)
    @JvmOverloads fun animateTextColour(colour: ColorConstraint, time: Float = 1f) = text.animate { setColorAnimation(Animations.OUT_EXP, time, colour) }

    companion object {
        val DEFAULT_WIDTH = 200
        val DEFAULT_WIDTH_PIXELS = DEFAULT_WIDTH.pixels()
        val DEFAULT_HEIGHT = 20
        val DEFAULT_HEIGHT_PIXELS = DEFAULT_HEIGHT.pixels()

        val DISABLED_COLOUR = Color(100, 100, 100)
    }

}
