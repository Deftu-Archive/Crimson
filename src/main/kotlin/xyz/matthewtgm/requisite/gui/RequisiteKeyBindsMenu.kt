package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.components.ScrollComponent
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIWrappedText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import xyz.matthewtgm.requisite.Requisite
import xyz.matthewtgm.requisite.gui.base.RequisiteWindowScreen

class RequisiteKeyBindsMenu : RequisiteWindowScreen(
    enableRepeatKeys = false,
    restoreCurrentGuiOnClose = true
) {

    val scrollable = ScrollComponent("No KeyBinds present.").constrain {
        x = 0.pixels()
        y = navbar.constraints.y + 2.pixels()
        width = 100.percent()
        height = 100.percent()
    } childOf background

    private val categoryMap = mutableMapOf<String,  UIContainer>()

    init {
        val keyBindList = Requisite.getManager().keyBindManager.keyBinds

        for (keyBind in keyBindList) {
            val category = keyBind.category()
            val categoryContainer = if (categoryMap.containsKey(category)) categoryMap[category] else makeCategoryContainer(category)

            val keyBindText = UIWrappedText(keyBind.name()).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(3f)
            } childOf categoryContainer!!
        }
    }

    private fun makeCategoryContainer(category: String): UIContainer {
        val container = UIContainer().constrain {
            x = CenterConstraint()
            y = SiblingConstraint(3f)
        } childOf scrollable

        val text = UIWrappedText(category).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(3f)
        } childOf container
        text.setTextScale(2.pixels())

        categoryMap[category] = container

        return container
    }

}