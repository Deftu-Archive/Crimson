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

package xyz.qalcyo.requisite.core.gui.main

import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.constrain
import xyz.qalcyo.requisite.core.RequisiteAPI
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

abstract class RequisiteMenuPage(
    val title: String,
    val icon: BufferedImage
) : UIContainer() {
    abstract fun initialize()

    fun reset() {
        clearChildren()
    }
}

fun imageFromString(path: String) = ImageIO.read(RequisiteAPI::class.java.getResourceAsStream(path))