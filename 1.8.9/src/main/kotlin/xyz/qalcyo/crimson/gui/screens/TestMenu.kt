/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.gui.screens

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import net.minecraft.client.Minecraft
import xyz.qalcyo.crimson.Crimson
import xyz.qalcyo.crimson.gui.components.builders.UIPlayerBuilder

/**
 * A test menu.
 * Will this be removed? Who knows.
 */
class TestMenu : WindowScreen() {

    init {
        val player = UIPlayerBuilder(Minecraft.getMinecraft().thePlayer, true).build(Crimson.getInstance().componentFactory).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf window
    }

}