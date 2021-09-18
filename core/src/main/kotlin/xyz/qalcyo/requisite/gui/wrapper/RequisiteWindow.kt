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

package xyz.qalcyo.requisite.gui.wrapper

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.ConstantColorConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.core.IRequisite
import java.awt.Color

class RequisiteWindow(
    requisite: IRequisite,
    window: Window,
    showParent: Any.() -> Unit,
    showNavbar: Boolean = true
) : UIComponent() {

    val background = UIBlock(Color(79, 79, 79, 156)).constrain {
        width = 100.percent()
        height = 100.percent()
    } childOf window



    val navbar = UIContainer().constrain {
        width = 100.percent()
        height = 20.pixels()
    } childOf background
    val navbarBackground = UIBlock(Color(71, 71, 71, 138)).constrain {
        width = 100.percent()
        height = 100.percent()
    } childOf navbar
    val navbarLine = UIBlock(Color.WHITE).constrain {
        width = 100.percent()
        height = 2.percent()
        y = 0.pixels(alignOpposite = true)
    } childOf navbar



    val titleTextContainer = UIContainer().constrain {
        x = 10.pixels()
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf navbar
    val titleText = UIText(requisite.name()).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    } childOf titleTextContainer
    init {
        titleText.setTextScale(2.pixels())
        titleTextContainer.onMouseEnter {
            titleText.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color(255, 187, 0)))
            }
        }.onMouseLeave {
            titleText.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color.WHITE))
            }
        }.onMouseClick {
            requisite.openMenu()
        }
    }



    val backContainer = UIContainer().constrain {
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
        x = 2.pixels(alignOpposite = true)
        y = CenterConstraint()
    } childOf navbar
    val backIcon = UIImage.ofResource("/gui/icons/exit.png").constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = 16.pixels()
        height = 16.pixels()
    } childOf backContainer
    init {
        backContainer.onMouseEnter {
            backIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color(255, 187, 0)))
            }
        }.onMouseLeave {
            backIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color.WHITE))
            }
        }.onMouseClick {
            this.showParent()
        }
    }

    init {
        if (!showNavbar) {
            navbar.hide(instantly = true)
        }
    }

}
