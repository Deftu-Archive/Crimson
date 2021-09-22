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

package xyz.qalcyo.requisite.gui

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.ConstantColorConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.gui.wrapper.RequisiteWindow
import java.awt.Color

class RequisiteMenuWindow(
    window: RequisiteWindow,

    showCosmetics: Any.() -> Unit,
    showHud: Any.() -> Unit,
    showKeyBinds: Any.() -> Unit
) : UIComponent() {

    val linkPadding = 2f
    val linkContainer = UIContainer().constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf window.background

    val cosmeticsContainer = UIContainer().constrain {
        x = SiblingConstraint(linkPadding)
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf linkContainer
    val cosmeticsIcon = UIImage.ofResource("/gui/icons/cosmetics.png").constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = 64.pixels()
        height = 64.pixels()
    } childOf cosmeticsContainer
    init {
        cosmeticsContainer.onMouseEnter {
            cosmeticsIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color(255, 187, 0)))
            }
        }.onMouseLeave {
            cosmeticsIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color.WHITE))
            }
        }.onMouseClick {
            this.showCosmetics()
        }
    }

    val hudContainer = UIContainer().constrain {
        x = SiblingConstraint(linkPadding)
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf linkContainer
    val hudIcon = UIImage.ofResource("/gui/icons/hud.png").constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = 64.pixels()
        height = 64.pixels()
    } childOf hudContainer
    init {
        hudContainer.onMouseEnter {
            hudIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color(255, 187, 0)))
            }
        }.onMouseLeave {
            hudIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color.WHITE))
            }
        }.onMouseClick {
            this.showHud()
        }
    }

    val keybindsContainer = UIContainer().constrain {
        x = SiblingConstraint(linkPadding)
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf linkContainer
    val keybindsIcon = UIImage.ofResource("/gui/icons/keybinds.png").constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = 64.pixels()
        height = 64.pixels()
    } childOf keybindsContainer
    init {
        keybindsContainer.onMouseEnter {
            keybindsIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color(255, 187, 0)))
            }
        }.onMouseLeave {
            keybindsIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color.WHITE))
            }
        }.onMouseClick {
            this.showKeyBinds()
        }
    }

}