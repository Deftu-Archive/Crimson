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

package xyz.qalcyo.requisite.core.gui.screens.main

import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import java.awt.Color
import java.util.concurrent.CompletableFuture

interface IRequisiteMenu {

    val window: Window

    val pages: Array<RequisiteMenuPage>

    var page: RequisiteMenuPage
    val title: UIText
    val content: UIContainer

    fun initialize() {
        val pageListContainer = UIContainer().constrain {
            width = ChildBasedSizeConstraint() / 2
            height = RelativeConstraint()
        } childOf window
        for (page in pages) {
            val pageButton = UIImage(CompletableFuture.supplyAsync { page.icon }).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(1f)
                width = 32.pixels()
                height = 32.pixels()
            }.onMouseClick {
                initializePage(page)
            } childOf pageListContainer
        }

        val pageListDivider = UIBlock(Color.BLACK).constrain {
            x = SiblingConstraint()
            width = 2.pixels()
            height = RelativeConstraint()
        } childOf window

        val titleContainer = UIContainer().constrain {
            x = SiblingConstraint()
            width = RelativeConstraint()
            height = ChildBasedSizeConstraint() * 2
        } childOf window

        title.constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        }.setTextScale(2.pixels()) childOf titleContainer

        val titleDivider = UIBlock(Color.BLACK).constrain {
            x = pageListDivider.getRight().pixels()
            y = titleContainer.getBottom().pixels()
            width = RelativeConstraint()
            height = 2.pixels()
        } childOf window

        content childOf window
        content.constrain {
            x = pageListDivider.getRight().pixels()
            y = titleDivider.getBottom().pixels()
            width = RelativeConstraint()
            height = RelativeConstraint()
        }

        for (page in pages) {
            page.constrain {
                width = RelativeConstraint()
                height = RelativeConstraint()
            } childOf content
        }

        page.reset()
        page.initialize()
    }

    fun initializePage(page: RequisiteMenuPage) {
        this@IRequisiteMenu.page = page
        title.setText(page.title)
        for (page in pages) {
            page.hide(true)
        }

        page.unhide(true)
        page.reset()
        page.initialize()
    }

}
