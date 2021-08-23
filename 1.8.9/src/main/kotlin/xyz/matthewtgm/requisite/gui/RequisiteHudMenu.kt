package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.childOf
import gg.essential.universal.UMatrixStack
import xyz.matthewtgm.requisite.Requisite
import xyz.matthewtgm.requisite.gui.wrapper.RequisiteWindow

class RequisiteHudMenu : WindowScreen() {

    private val requisiteWindow = RequisiteWindow(Requisite.getInstance(), window, {
        restorePreviousScreen()
    }) childOf window
    private val menu = RequisiteHudMenuWindow(Requisite.getInstance()) childOf requisiteWindow

    override fun onDrawScreen(matrixStack: UMatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        menu.draw(Requisite.getInstance(), partialTicks)
        super.onDrawScreen(matrixStack, mouseX, mouseY, partialTicks)
    }

    override fun onScreenClose() {
        menu.close(Requisite.getInstance())
        super.onScreenClose()
    }

}
