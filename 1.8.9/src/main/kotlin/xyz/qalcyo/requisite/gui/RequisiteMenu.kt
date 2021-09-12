package xyz.qalcyo.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import net.minecraft.client.Minecraft
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.gui.wrapper.RequisiteWindow

class RequisiteMenu : WindowScreen(
    drawDefaultBackground = false,
    restoreCurrentGuiOnClose = true,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
) {
    init {
        val window = RequisiteWindow(Requisite.getInstance(), window, {
            restorePreviousScreen()
        }) childOf this.window

        val menu = RequisiteMenuWindow(window, {

        }, {
        }, {

        }) childOf window
    }

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }
}
