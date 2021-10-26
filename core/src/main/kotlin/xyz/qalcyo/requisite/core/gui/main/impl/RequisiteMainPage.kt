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

import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.core.RequisiteAPI
import xyz.qalcyo.requisite.core.gui.components.InteractableText
import xyz.qalcyo.requisite.core.gui.main.RequisiteMenuPage
import xyz.qalcyo.requisite.core.gui.main.*

class RequisiteMainPage : RequisiteMenuPage("Requisite", imageFromString("/gui/home.png")) {
    override fun initialize() {
        val requisite = RequisiteAPI.retrieveInstance()
        val creditsText = InteractableText("Credits", true, InteractableText.Alignment.CENTER, {
            requisite.openCreditsMenu()
        }).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        }.setTextScale(2.pixels()) childOf this
    }
}
