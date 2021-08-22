package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import xyz.matthewtgm.requisite.Requisite
import xyz.matthewtgm.requisite.gui.wrapper.RequisiteWindow

class RequisiteMenu : WindowScreen() {
    init {
        window.addChild(RequisiteWindow(Requisite.getInstance(), window, {
            restorePreviousScreen()
        }))
    }
}
