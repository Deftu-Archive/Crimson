package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import net.minecraft.client.Minecraft
import java.awt.Color

open class RequisiteWindowScreen(
    enableRepeatKeys: Boolean = true,
    restoreCurrentGuiOnClose: Boolean = false,
) : WindowScreen(
    enableRepeatKeys,
    false,
    restoreCurrentGuiOnClose,
    GuiScale.scaleForScreenSize().ordinal
) {

    val background = UIBlock(Color(112, 112, 112, 165)).constrain {
        width = 100.percent()
        height = 100.percent()
    } childOf window

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }

}
