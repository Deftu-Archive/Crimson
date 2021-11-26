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

package xyz.qalcyo.crimson.core.gui.screens.main

import gg.essential.elementa.components.UIContainer
import xyz.qalcyo.crimson.core.gui.screens.main.impl.CrimsonControlsPage
import xyz.qalcyo.crimson.core.gui.screens.main.impl.CrimsonNetworkingPage

abstract class CrimsonMenuPage(
    val title: String
) : UIContainer() {
    abstract fun initialize()

    fun reset() {
        clearChildren()
    }

    open fun keyTyped(typedChar: Char, keyCode: Int): Boolean {
        return false
    }

    companion object {
        val pages = arrayOf(
            CrimsonControlsPage(),
            CrimsonNetworkingPage()
        )

        fun getPageIndex(pageClass: Class<out CrimsonMenuPage>): Int {
            var index = 0
            for (page in pages) {
                if (page.javaClass == pageClass) {
                    index = pages.indexOf(page)
                }
            }

            return index
        }

    }
}