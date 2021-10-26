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

package xyz.qalcyo.requisite.core.gui.components

import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import xyz.qalcyo.requisite.core.*
import xyz.qalcyo.requisite.core.gui.components.builders.*
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

    private var hovering = false

    init {
        constrain {
            width = DEFAULT_WIDTH_PIXELS
            height = DEFAULT_HEIGHT_PIXELS
        }

        if (!builder.toggled) {
            animateTextColour(DISABLED_TEXT_COLOUR)
            animateBorder(DISABLED_BORDER_COLOUR)
        }

        onMouseEnter {
            hovering = true
            animateBorder(if (!builder.toggled) RequisitePalette.getMain().asColor().darker() else RequisitePalette.getMain().asColor())
        }.onMouseLeave {
            hovering = false
            animateBorder(if (!builder.toggled) DISABLED_BORDER_COLOUR else Color(0, 0, 0, 0))
        }.onMouseClick {
            if (builder.toggled) {
                builder.action.invoke(this@Button)
            }
        }
    }

    fun setText(input: String) = text.setText(input)
    fun setTextColour(colour: Color, animate: Boolean = true) = if (animate) animateTextColour(colour) else text.setColor(colour)
    fun setToggled(toggled: Boolean) {
        animateTextColour(if (toggled) Color.WHITE else DISABLED_TEXT_COLOUR)
        animateBorder(if (!toggled) DISABLED_BORDER_COLOUR else if (hovering) RequisitePalette.getMain().asColor() else Color(0, 0, 0, 0))
        builder.toggled = toggled
    }

    fun toggle(): Boolean {
        setToggled(!builder.toggled)
        return builder.toggled
    }

    @JvmOverloads fun animateTextColour(colour: Color, time: Float = 1f) = animateTextColour(ConstantColorConstraint(colour), time)
    @JvmOverloads fun animateTextColour(colour: ColorConstraint, time: Float = 1f) = text.animate { setColorAnimation(Animations.OUT_EXP, time, colour) }
    @JvmOverloads fun animateBorder(colour: Color, time: Float = 1f) = border.animate {
        (border.effects[0] as OutlineEffect)::color.animate(Animations.OUT_EXP, time, colour)
    }

    companion object {
        val DEFAULT_WIDTH = 200
        val DEFAULT_WIDTH_PIXELS = DEFAULT_WIDTH.pixels()
        val DEFAULT_HEIGHT = 20
        val DEFAULT_HEIGHT_PIXELS = DEFAULT_HEIGHT.pixels()

        val DEFAULT_WIDTH_SMALL = 100
        val DEFAULT_WIDTH_SMALL_PIXELS = DEFAULT_WIDTH_SMALL.pixels()

        val DISABLED_TEXT_COLOUR = Color(100, 100, 100)
        val DISABLED_BORDER_COLOUR = Color.RED.darker()
    }

}
