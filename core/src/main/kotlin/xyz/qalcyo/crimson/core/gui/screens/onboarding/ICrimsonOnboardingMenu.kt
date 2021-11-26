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

package xyz.qalcyo.crimson.core.gui.screens.onboarding

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIRoundedRectangle
import gg.essential.elementa.components.Window
import gg.essential.elementa.components.inspector.Inspector
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import xyz.qalcyo.crimson.core.CrimsonPalette
import java.awt.Color

interface ICrimsonOnboardingMenu {
    
    val window: Window
    var initialized: Boolean

    var slide: CrimsonOnboardingSlideBase
    val content: UIContainer

    var slideBox: SlideBox
    val slideBoxes: MutableList<SlideBox>
    
    fun initialize() {
        reset()

        val background = UIBlock(CrimsonPalette.getSecondary().asColor()).constrain {
            width = RelativeConstraint()
            height = RelativeConstraint()
        } childOf window

        val slideBoxContainer = UIContainer().constrain {
            x = CenterConstraint()
            y = 15.pixels(true)
            width = ChildBasedSizeConstraint(5f)
            height = ChildBasedSizeConstraint()
        } childOf window
        for (slide in CrimsonOnboardingSlideBase.slides) {
            val slideBox = SlideBox().constrain {
                x = SiblingConstraint(5f)
                y = CenterConstraint()
            }.onMouseClick {
                println("Click! ${slideBoxes.indexOf(this)}")
                update(slideBoxes.indexOf(this))
            } childOf slideBoxContainer
            slideBoxes.add(slideBox as SlideBox)

            slide.constrain {
                width = RelativeConstraint()
                height = RelativeConstraint()
            } childOf content
        }

        content childOf window
        content.constrain {
            x = 50.pixels()
            y = 40.pixels()
            width = 85.percent()
            height = 80.percent()
        }

        update(0)

        Inspector(window) childOf window
        initialized = true
    }

    fun reset() {
        window.clearChildren()
        slideBoxes.clear()
        for (slide in CrimsonOnboardingSlideBase.slides) {
            slide.reset()
        }
    }

    fun update(index: Int) {
        setSlideBox(index)
        setSlide(index)
    }

    fun setSlideBox(index: Int) {
        if (index > slideBoxes.size) throw IllegalArgumentException("Index > size")
        val new = slideBoxes[index]
        slideBox = new
        for (slideBox in slideBoxes)
            slideBox.reset()
        slideBox.select()
    }

    fun setSlide(index: Int) {
        if (index > CrimsonOnboardingSlideBase.slides.size) throw IllegalArgumentException("Index > size")
        val previousSlide = slide
        slide = CrimsonOnboardingSlideBase.slides[index]
        if (initialized && previousSlide != slide) previousSlide.hide()
        slide.unhide(true)
        slide.reset()
        slide.initialize()
    }
    
}

class SlideBox : UIRoundedRectangle(3f) {
    init {
        constrain {
            width = NORMAL_WIDTH
            height = 7.5f.pixels()
            color = Color.GRAY.toConstraint()
        }
    }

    fun select() {
        animate {
            setWidthAnimation(Animations.OUT_EXP, 1f, SELECTED_WIDTH)
            setColorAnimation(Animations.OUT_EXP, 0.25f, CrimsonPalette.getPrimary().asColor().toConstraint())
        }
    }

    fun reset() {
        animate {
            setWidthAnimation(Animations.OUT_EXP, 1f, NORMAL_WIDTH)
            setHeightAnimation(Animations.OUT_EXP, 1f, 5.pixels())
            setColorAnimation(Animations.OUT_EXP, 0.25f, Color.GRAY.toConstraint())
        }
    }

    companion object {
        val NORMAL_WIDTH = 10.pixels()
        val SELECTED_WIDTH = 40.pixels()
    }
}