package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import gg.essential.universal.USound
import net.minecraft.client.Minecraft
import xyz.matthewtgm.requisite.Requisite
import java.awt.Color

open class RequisiteWindowScreen(
    enableRepeatKeys: Boolean = true,
    restoreCurrentGuiOnClose: Boolean = false,
    showNavbar: Boolean = true
) : WindowScreen(
    enableRepeatKeys,
    false,
    restoreCurrentGuiOnClose,
    GuiScale.scaleForScreenSize().ordinal
) {

    val background = UIBlock(Color(79, 79, 79, 156)).constrain {
        width = 100.percent()
        height = 100.percent()
    } childOf window



    val navbar = UIContainer().constrain {
        width = 100.percent()
        height = 8.percent()
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
    val titleText = UIText(Requisite.getName()).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    } childOf titleTextContainer
    init {
        titleText.setTextScale(2.pixels())
        titleTextContainer.onMouseEnter {
            titleText.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, Color(255, 187, 0).toConstraint())
            }
        }.onMouseLeave {
            titleText.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, Color.WHITE.toConstraint())
            }
        }.onMouseClick {
            USound.playButtonPress()
            displayScreen(RequisiteMenu())
        }
    }



    val backContainer = UIContainer().constrain {
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
        x = 2.pixels(alignOpposite = true)
        y = CenterConstraint()
    } childOf navbar
    val backIcon = UIImage.ofResource("/assets/requisite/gui/icons/exit.png").constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        width = 36.pixels()
        height = 36.pixels()
    } childOf backContainer
    init {
        backContainer.onMouseEnter {
            backIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, Color(255, 187, 0).toConstraint())
            }
        }.onMouseLeave {
            backIcon.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, Color.WHITE.toConstraint())
            }
        }.onMouseClick {
            USound.playButtonPress()
            restorePreviousScreen()
        }
    }

    init {
        if (!showNavbar) {
            navbar.hide(instantly = true)
        }
    }

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }

}
