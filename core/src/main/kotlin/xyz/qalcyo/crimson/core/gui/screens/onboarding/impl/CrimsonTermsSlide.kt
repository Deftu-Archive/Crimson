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

import gg.essential.elementa.dsl.*
import xyz.qalcyo.crimson.core.CrimsonAPI
import xyz.qalcyo.crimson.core.CrimsonPalette
import xyz.qalcyo.crimson.core.gui.components.builders.ButtonBuilder
import xyz.qalcyo.crimson.core.gui.screens.onboarding.CrimsonOnboardingSlideBase

class CrimsonTermsSlide : CrimsonOnboardingSlideBase() {
    override fun initialize() {
        val crimson = CrimsonAPI.retrieveInstance()
        val acceptButton = crimson.componentFactory.build(ButtonBuilder({
            crimson.configManager.onboarding.isTos = true
            crimson.configManager.onboarding.isPrompted = true
            crimson.onOnboardingAccepted()
        }, "Accept", disableBorderAnimations = true)).constrain {
            y = 5.pixels(true)
        } childOf this
        acceptButton.setTextColour(CrimsonPalette.getSuccess().asColor())
        acceptButton.setBorderColour(CrimsonPalette.getSuccess().asColor())
        val denyButton = crimson.componentFactory.build(ButtonBuilder({
            crimson.configManager.onboarding.isTos = false
            crimson.configManager.onboarding.isPrompted = true
            crimson.onOnboardingDenied()
        }, "Deny", disableBorderAnimations = true)) childOf this
        denyButton.setTextColour(CrimsonPalette.getFail().asColor())
        denyButton.setBorderColour(CrimsonPalette.getFail().asColor())
    }
}