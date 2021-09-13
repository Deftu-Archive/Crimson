package xyz.qalcyo.requisite

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.popups.FixedPopup
import java.awt.Color

class Test {

    fun execute(requisite: Requisite) {
        requisite.popupRegistry.add(FixedPopup(
            "test",
            "Test Popup",
            UIBlock(Color.BLACK).constrain {
                width = RelativeConstraint()
                height = RelativeConstraint()
            },
            128f,
            128f
        ))
    }

}
