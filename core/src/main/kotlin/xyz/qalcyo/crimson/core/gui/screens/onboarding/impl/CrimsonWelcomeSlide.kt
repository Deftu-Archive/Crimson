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

package xyz.qalcyo.crimson.core.gui.screens.onboarding.impl

import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import xyz.qalcyo.crimson.core.CrimsonAPI
import xyz.qalcyo.crimson.core.gui.screens.onboarding.CrimsonOnboardingSlideBase
import xyz.qalcyo.crimson.core.util.ChatColour

class CrimsonWelcomeSlide  : CrimsonOnboardingSlideBase() {
    override fun initialize() {
        val crimson = CrimsonAPI.retrieveInstance()

        val welcomeText = UIText("Welcome to ${ChatColour.DARK_RED}Crimson").constrain {
            x = CenterConstraint()
            y = 2.pixels()
        }.setTextScale(3.pixels()) childOf this
        val modList = crimson.bridge.minecraftBridge.crimsonModList
        if (modList.isNotEmpty()) {
            val installedText = UIText("It was installed by:").constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            }.setTextScale(2.pixels()) childOf this
            val modListText = UIText("${ChatColour.DARK_AQUA}${modList.joinToString()}").constrain {
                x = CenterConstraint()
                y = SiblingConstraint(1f)
            }.setTextScale(2.pixels()) childOf this
        }
    }
}