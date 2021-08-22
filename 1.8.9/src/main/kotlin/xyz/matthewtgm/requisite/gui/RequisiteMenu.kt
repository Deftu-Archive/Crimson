package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.*
import xyz.matthewtgm.requisite.Requisite
import xyz.matthewtgm.requisite.gui.wrapper.RequisiteWindow

class RequisiteMenu : WindowScreen() {
    init {
        val window = RequisiteWindow(Requisite.getInstance(), window, {
            restorePreviousScreen()
        }) childOf this.window

        val menu = RequisiteMenuWindow(window, {

            }, {

        }, {

        }) childOf window
    }
}
