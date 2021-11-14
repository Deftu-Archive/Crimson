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

package xyz.qalcyo.requisite.core.gui.screens.main.impl

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.core.RequisiteAPI
import xyz.qalcyo.requisite.core.RequisitePalette
import xyz.qalcyo.requisite.core.gui.components.Button
import xyz.qalcyo.requisite.core.gui.components.builders.ButtonBuilder
import xyz.qalcyo.requisite.core.gui.screens.main.RequisiteMenuPage
import xyz.qalcyo.requisite.core.keybinds.KeyBind
import java.awt.Color

class RequisiteControlsPage : RequisiteMenuPage("Controls") {
    private var selected: KeyBind? = null
    private val selection
        get() = selected != null
    private var button: Button? = null
    override fun initialize() {
        val requisite = RequisiteAPI.retrieveInstance()
        val categories = mutableMapOf<String, Category>()

        for (keyBind in requisite.keyBindRegistry.keyBinds) {
            val existed = categories.containsKey(keyBind.name)
            categories.putIfAbsent(keyBind.category, Category(existed, UIText(keyBind.category)))

            val category = categories[keyBind.category]!!
            val categoryContainer = category.container
            if (!existed) {
                categoryContainer childOf this
            }

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

            val keyBindDivider = UIBlock(Color.BLACK).constrain {
                x = SiblingConstraint(2f)
                y = CenterConstraint()
                width = 7.pixels()
                height = 2.pixels()
            } childOf keyBindContainer

            val keyBindButton = (ButtonBuilder({
                selected = keyBind
                button = this
                animateBorder(RequisitePalette.getSuccess().asColor())
            }, if (keyBind.key == -1) "None" else requisite.keyboardHelper.getKeyName(keyBind.key)).build(requisite.componentFactory).constrain {
                x = SiblingConstraint(2f)
                y = CenterConstraint()
                width = Button.DEFAULT_WIDTH_SMALL_PIXELS
            }.onMouseLeave {
                val btn = this as Button
                if (selection && button == btn) {
                    btn.animateBorder(RequisitePalette.getSuccess().asColor())
                }
            } childOf keyBindContainer) as Button
        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int): Boolean {
        val requisite = RequisiteAPI.retrieveInstance()
        if (selection) {
            selected!!.key = if (requisite.keyboardHelper.isEscapeKey(keyCode)) -1 else if (keyCode == 0) typedChar.code else keyCode
            RequisiteAPI.retrieveInstance().keyBindRegistry.save(selected)
            button!!.animateBorder(Color(0, 0, 0, 0))
            button!!.setText(if (requisite.keyboardHelper.isEscapeKey(keyCode)) "None" else requisite.keyboardHelper.getKeyName(keyCode))
            button = null
            selected = null
            return true
        }

        return false
    }
}

private class Category(
    preExistence: Boolean,
    title: UIText
) {
    var container = UIContainer().constrain {
        x = CenterConstraint()
        y = (if (!preExistence) 5 else 0).pixels() + SiblingConstraint(1f)
        width = RelativeConstraint()
        height = ChildBasedSizeConstraint(1f)
    }

    init {
        title.constrain {
            x = CenterConstraint()
        }.setTextScale(2.pixels()) childOf container
    }
}