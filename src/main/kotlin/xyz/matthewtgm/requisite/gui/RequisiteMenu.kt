package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import net.minecraft.client.Minecraft
import xyz.matthewtgm.requisite.Requisite
import java.awt.Color

class RequisiteMenu : WindowScreen(
    restoreCurrentGuiOnClose = true,
    drawDefaultBackground = false,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
) {
    init {
        val background = UIBlock(Color(112, 112, 112, 165)).constrain {
            width = 100.percent()
            height = 100.percent()
        } childOf window

        val titleBar = UIBlock(Color(0, 0, 0, 0)).constrain {
            width = 100.percent()
            height = 6.percent()
        } childOf background
        val titleBarLine = UIBlock(Color(165, 165, 165, 189)).constrain {
            width = 100.percent()
            height = 10.percent()
            y = titleBar.getHeight().pixels()
        } childOf titleBar

        val titleText = UIText(Requisite.NAME).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf titleBar
        titleText.setTextScale(2.pixels())
    }

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }
}
