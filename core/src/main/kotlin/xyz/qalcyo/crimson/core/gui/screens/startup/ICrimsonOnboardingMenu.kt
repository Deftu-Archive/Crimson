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

package xyz.qalcyo.crimson.core.gui.screens.startup

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.Window
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.utils.withAlpha
import xyz.qalcyo.crimson.core.CrimsonAPI
import xyz.qalcyo.crimson.core.CrimsonPalette
import xyz.qalcyo.crimson.core.gui.components.Button
import xyz.qalcyo.crimson.core.gui.components.builders.ButtonBuilder
import java.awt.Color

interface ICrimsonOnboardingMenu {
    
    val window: Window
    
    val slides: Array<CrimsonOnboardingSlideBase>
    var slide: CrimsonOnboardingSlideBase
    val content: UIContainer

    var slideBox: UIBlock
    val slideBoxes: MutableList<UIBlock>
    
    fun initialize() {
    }
    
}