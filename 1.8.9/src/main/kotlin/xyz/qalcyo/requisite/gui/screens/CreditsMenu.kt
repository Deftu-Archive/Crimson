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
import gg.essential.elementa.components.ScrollComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedRangeConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import gg.essential.universal.UDesktop
import xyz.qalcyo.json.entities.JsonArray
import xyz.qalcyo.json.util.JsonApiHelper
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.RequisiteConstants
import xyz.qalcyo.requisite.core.RequisitePalette
import xyz.qalcyo.requisite.gui.components.Button
import xyz.qalcyo.requisite.gui.components.InteractableText
import xyz.qalcyo.requisite.gui.components.builders.ButtonBuilder
import java.net.URI

class CreditsMenu : WindowScreen(
    ElementaVersion.V1,
    restoreCurrentGuiOnClose = true
) {

    init {
        val creditsJson = JsonApiHelper.getJsonObject(RequisiteConstants.CREDITS_URL, false)
        val librariesArray = creditsJson.getAsArray("libraries")
        val codeAuthorsArray = creditsJson.getAsArray("code_authors")

        val scrollable = ScrollComponent().constrain {
            x = 0.pixels()
            y = 0.pixels()
            width = RelativeConstraint()
            height = RelativeConstraint() - 50.pixels()
        } childOf window
        val scrollableBar = ScrollComponent.DefaultScrollBar(false) childOf scrollable
        scrollable.setVerticalScrollBarComponent(scrollableBar, true)
        scrollable.setEmptyText("I'm empty :(")

        val librariesContainer = UIContainer().constrain {
            width = RelativeConstraint()
            height = ChildBasedRangeConstraint()
        } childOf scrollable
        val librariesTitle = UIText("Libraries").constrain {
            x = CenterConstraint()
            y = 2.pixels()
        } childOf librariesContainer
        librariesTitle.setTextScale(2.pixels())
        appendUsingSchema(librariesContainer, librariesArray)

        val codeAuthorsContainer = UIContainer().constrain {
            y = SiblingConstraint(3f)
            width = RelativeConstraint()
            height = ChildBasedRangeConstraint()
        } childOf scrollable
        val codeAuthorsTitle = UIText("Code authors").constrain {
            x = CenterConstraint()
            y = 2.pixels()
        } childOf codeAuthorsContainer
        codeAuthorsTitle.setTextScale(2.pixels())
        appendUsingSchema(codeAuthorsContainer, codeAuthorsArray)

        val divider = UIBlock(RequisitePalette.getComponentContent().asColor()).constrain {
            x = 0.pixels()
            y = 50.pixels(true)
            width = RelativeConstraint()
            height = 2.pixel()
        } childOf window

        val backButton = ButtonBuilder({
            restorePreviousScreen()
        }, "Back").build(Requisite.getInstance().componentFactory).constrain {
            x = CenterConstraint()
            y = 12.5f.pixels(true)
            width = Button.DEFAULT_WIDTH_SMALL_PIXELS
            height = Button.DEFAULT_HEIGHT_PIXELS
        } childOf window
    }

    private fun appendUsingSchema(container: UIContainer, array: JsonArray) {
        for (element in array) {
            if (element.isJsonObject) {
                val content = element.asJsonObject
                if (content.hasKey("name")) {
                    var text = (if (content.hasKey("url")) InteractableText(content.getAsString("name"), hoverUnderline = true, InteractableText.Alignment.CENTER, {
                        UDesktop.browse(URI.create(content.getAsString("url")))
                    }) else UIText(content.getAsString("name"))).constrain {
                        x = CenterConstraint()
                        y = SiblingConstraint(2f)
                    } childOf container
                }
            }
        }
    }

}