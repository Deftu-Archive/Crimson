/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.core.gui.main.impl

import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.Window
import gg.essential.elementa.components.inspector.Inspector
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.core.RequisiteAPI
import xyz.qalcyo.requisite.core.gui.components.Button
import xyz.qalcyo.requisite.core.gui.components.builders.ButtonBuilder
import xyz.qalcyo.requisite.core.gui.main.RequisiteMenuPage
import xyz.qalcyo.requisite.core.gui.main.*
import xyz.qalcyo.requisite.core.keybinds.KeyBind
import java.awt.Color

class RequisiteControlsPage : RequisiteMenuPage("Controls", imageFromString("/gui/controls.png")) {
    private var selected: KeyBind? = null
    private val selection
        get() = selected != null
    private var button: Button? = null
    override fun initialize() {
        val requisite = RequisiteAPI.retrieveInstance()
        val categories = mutableMapOf<String, Category>()

        for (keyBind in requisite.keyBindRegistry.keyBinds) {
            categories.putIfAbsent(keyBind.category, Category(UIText(keyBind.category)))
            val category = categories[keyBind.category]!!
            val categoryContainer = category.container
            categoryContainer childOf this

            val keyBindContainer = UIContainer().constrain {
                x = CenterConstraint()
                y = SiblingConstraint(1f)
                width = ChildBasedSizeConstraint()
                height = ChildBasedSizeConstraint()
            } childOf categoryContainer

            val keyBindName = UIText(keyBind.name).constrain {
                x = 2.pixels()
                y = CenterConstraint()
            } childOf keyBindContainer

            button = (ButtonBuilder({
                selected = keyBind
                animateBorder(Color.GREEN)
            }, requisite.keyboardHelper.getKeyName(keyBind.key)).build(requisite.componentFactory).constrain {
                x = 2.pixels(true)
                y = CenterConstraint()
                width = Button.DEFAULT_WIDTH_SMALL_PIXELS
            } childOf keyBindContainer) as Button
        }

        onKeyType { typedChar, keyCode ->
            if (selection && !requisite.keyboardHelper.isEscapeKey(keyCode)) {
                selected!!.key = if (keyCode == 0) typedChar.code else keyCode
                RequisiteAPI.retrieveInstance().keyBindRegistry.save(selected)
                button!!.animateBorder(Color(0, 0, 0, 0))
                button!!.setText(requisite.keyboardHelper.getKeyName(keyCode))
            }
        }

        try {
            val inspector = Inspector(Window.of(this)) childOf Window.of(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

private class Category(
    title: UIText
) {
    var container = UIContainer().constrain {
        x = CenterConstraint()
        y = SiblingConstraint(1f)
        width = RelativeConstraint()
        height = ChildBasedSizeConstraint(1f)
    }

    init {
        title.constrain {
            x = CenterConstraint()
        }.setTextScale(2.pixels()) childOf container
    }
}