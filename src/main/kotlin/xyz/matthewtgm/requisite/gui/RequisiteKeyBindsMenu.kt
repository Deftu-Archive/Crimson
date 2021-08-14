package xyz.matthewtgm.requisite.gui

import gg.essential.elementa.components.ScrollComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIWrappedText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import xyz.matthewtgm.mango.annotations.Unfinished
import xyz.matthewtgm.requisite.gui.base.RequisiteWindowScreen
import java.awt.Color

@Unfinished
class RequisiteKeyBindsMenu : RequisiteWindowScreen(
    enableRepeatKeys = false,
    restoreCurrentGuiOnClose = true
) {

    private val scrollableContainer = UIContainer().constrain {
        x = 0.pixels()
        y = SiblingConstraint(2f)
        width = ChildBasedSizeConstraint()
        height = ChildBasedSizeConstraint()
    } childOf background
    private val scrollable = ScrollComponent(emptyString = "No KeyBinds present.", pixelsPerScroll = 25f).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    } childOf scrollableContainer

    private val categoryMap = mutableMapOf<String,  UIContainer>()

    init {
        repeat(5) {
            UIBlock(Color.RED).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(2f)
                width = 10.pixels()
                height = 10.pixels()
            } childOf scrollable
        }

        println("Scrollable: $scrollable")
        println("Category map: $categoryMap")

        /* Handle KeyBinds. */
        val keyBindList = Requisite.getManager().keyBindManager.keyBinds
        println("KeyBind list: $keyBindList")

        for (keyBind in keyBindList) {
            val category = keyBind.category()
            println("KeyBind category: ${keyBind.name()} | $category")
            val categoryContainer =
                if (categoryMap.containsKey(category)) categoryMap[category] else makeCategoryContainer(category)

            val keyBindText = UIWrappedText(keyBind.name()).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(3f)
            } childOf categoryContainer!!

            println("Parent of text: ${keyBindText.parent}")

            categoryContainer childOf scrollable
            categoryMap[category] = categoryContainer
            println("Category map modi: $categoryMap")
        }
    }

    private fun makeCategoryContainer(category: String): UIContainer {
        val container = UIContainer().constrain {
            x = CenterConstraint()
            y = SiblingConstraint(3f)
        }

        val text = UIWrappedText(category).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(3f)
        } childOf container
        text.setTextScale(2.pixels())

        return container
    }

}