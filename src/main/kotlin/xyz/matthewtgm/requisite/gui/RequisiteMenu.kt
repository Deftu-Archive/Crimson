package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.ConstantColorConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.universal.USound
import xyz.matthewtgm.requisite.gui.base.RequisiteWindowScreen
import java.awt.Color

class RequisiteMenu : RequisiteWindowScreen(
    restoreCurrentGuiOnClose = true
) {

    val linkPadding = 2f
    val linkContainer = UIContainer().constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf background

    val cosmeticsContainer = UIContainer().constrain {
        x = SiblingConstraint(linkPadding)
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf linkContainer
    val cosmeticsIcon = UIImage.ofResource("/assets/requisite/gui/icons/cosmetics.png").constrain {
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
            USound.playButtonPress()
            restorePreviousScreen()
        }
    }

    val hudContainer = UIContainer().constrain {
        x = SiblingConstraint(linkPadding)
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf linkContainer
    val hudIcon = UIImage.ofResource("/assets/requisite/gui/icons/hud.png").constrain {
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
            USound.playButtonPress()
            displayScreen(RequisiteHudMenu())
        }
    }

    val keybindsContainer = UIContainer().constrain {
        x = SiblingConstraint(linkPadding)
        y = CenterConstraint()
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf linkContainer
    val keybindsIcon = UIImage.ofResource("/assets/requisite/gui/icons/keybinds.png").constrain {
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
            USound.playButtonPress()
            displayScreen(RequisiteHudMenu())
        }
    }

}
