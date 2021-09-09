package xyz.deftu.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import gg.essential.universal.UMatrixStack
import net.minecraft.client.Minecraft
import xyz.deftu.requisite.Requisite
import xyz.deftu.requisite.gui.wrapper.RequisiteWindow

class RequisiteHudMenu : WindowScreen(
    drawDefaultBackground = false,
    restoreCurrentGuiOnClose = true,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
) {

    private val requisiteWindow = RequisiteWindow(Requisite.getInstance(), window, {
        restorePreviousScreen()
    }) childOf window
    private val menu = RequisiteHudMenuWindow(Requisite.getInstance()) childOf requisiteWindow

    override fun onMouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int) {
        menu.mouseClick(mouseX, mouseY, mouseButton)
        super.onMouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun onDrawScreen(matrixStack: UMatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        menu.draw(Requisite.getInstance(), partialTicks)
        super.onDrawScreen(matrixStack, mouseX, mouseY, partialTicks)
    }

    override fun onScreenClose() {
        menu.close(Requisite.getInstance())
        super.onScreenClose()
    }

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }


}
