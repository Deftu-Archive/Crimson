package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import net.minecraft.client.Minecraft
import xyz.matthewtgm.requisite.Requisite
import java.awt.Color

class RequisiteMenu : RequisiteWindowScreen(
    restoreCurrentGuiOnClose = true
) {
    init {
        val titleBar = UIBlock(Color(0, 0, 0, 0)).constrain {
            width = 100.percent()
            height = 6.percent()
        } childOf background
        val titleBarLine = UIBlock(Color(165, 165, 165, 189)).constrain {
            width = 100.percent()
            height = 10.percent()
            y = titleBar.getHeight().pixels()
        } childOf titleBar

        val titleText = UIText(Requisite.getName()).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf titleBar
        titleText.setTextScale(2.pixels())
    }
}
