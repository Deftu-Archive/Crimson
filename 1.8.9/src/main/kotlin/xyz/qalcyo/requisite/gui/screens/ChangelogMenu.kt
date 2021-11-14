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

package xyz.qalcyo.requisite.gui.screens

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.dsl.*
import gg.essential.elementa.markdown.MarkdownComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.RequisiteConstants

class ChangelogMenu : WindowScreen(
    ElementaVersion.V1,
    restoreCurrentGuiOnClose = true
) {
    init {
        val httpClient = OkHttpClient()
        val text = httpClient.newCall(Request.Builder()
            .url(String.format(RequisiteConstants.CHANGELOG_URL_UNFORMATTED, Requisite.getInstance().version()))
            .get()
            .build()).execute()
            .body!!.string()
        if (text == "404: Not Found") {
            Requisite.getInstance().notifications.push("Requisite", "Changelogs are unavailable.")
        }

        val markdown = MarkdownComponent(text).constrain {
            x = CenterConstraint()
            y = 2.pixels()
            width = RelativeConstraint()
            height = RelativeConstraint()
        } childOf window
    }
}