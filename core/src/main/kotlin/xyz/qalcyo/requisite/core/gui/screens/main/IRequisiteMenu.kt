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
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import xyz.qalcyo.requisite.core.RequisitePalette
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.concurrent.CompletableFuture

interface IRequisiteMenu {

    val window: Window
    var initialized: Boolean

    val pages: Array<RequisiteMenuPage>

    var page: RequisiteMenuPage

    val header: RequisiteMenuHeader
    val content: UIContainer
    val footer: RequisiteMenuFooter

    fun openPreviousScreen()
    fun closeScreen()

    fun initialize() {
        initialized = false
        val window = UIContainer().constrain {
            x = 20.pixels()
            y = 20.pixels()
            width = 80.percent()
            height = 80.percent()
        } childOf window
        val background = UIBlock(RequisitePalette.getTertiary().asColor()).constrain {
            width = RelativeConstraint()
            height = RelativeConstraint()
        } childOf window
        header.constrain {
            width = RelativeConstraint()
            height = ChildBasedSizeConstraint()
        } childOf window
        val headerDivider = UIBlock(RequisitePalette.getPrimary().asColor()).constrain {
            y = SiblingConstraint()
            width = RelativeConstraint()
            height = 2.pixels()
        } childOf window

        footer.constrain {
            y = 0.pixels(true)
            width = RelativeConstraint()
            height = ChildBasedSizeConstraint()
        } childOf window
        val footerDivider = UIBlock(RequisitePalette.getPrimary().asColor()).constrain {
            y = SiblingConstraint(alignOpposite = true)
            width = RelativeConstraint()
            height = 2.pixels()
        } childOf window

        content childOf window
        content.constrain {
            y = headerDivider.getBottom().pixels()
            width = RelativeConstraint()
            height = FillConstraint()
        }

        for (page in pages) {
            page.constrain {
                width = RelativeConstraint()
                height = RelativeConstraint()
            } childOf content
        }

        initializePage(page)
        initialized = true
    }

    fun initializePage(page: RequisiteMenuPage) {
        header.setTitle(page.title)

        footer.setPageNumber(pages.indexOf(page) + 1, pages.size)
        footer.previousPage = if (initialized) this@IRequisiteMenu.page else null
        footer.nextPage = if (pages.indexOf(page) == pages.size - 1) null else pages[pages.indexOf(page) + 1]

        this@IRequisiteMenu.page = page

        for (page in pages)
            page.hide(true)

        page.unhide(true)
        page.reset()
        page.initialize()
    }

}

class RequisiteMenuHeader(
    menu: IRequisiteMenu,
    backButtonImage: BufferedImage,
    closeButtonImage: BufferedImage
) : UIContainer() {
    private val backButton = UIImage(CompletableFuture.supplyAsync { backButtonImage }).constrain {
        y = CenterConstraint()
        width = IMAGE_DIMENSIONS
        height = IMAGE_DIMENSIONS
    }.onMouseClick {
        menu.openPreviousScreen()
    } childOf this
    private val titleText = UIText().constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    }.setTextScale(2.pixels()) childOf this
    private val closeButton = UIImage(CompletableFuture.supplyAsync { closeButtonImage }).constrain {
        x = 0.pixels(true)
        y = CenterConstraint()
        width = IMAGE_DIMENSIONS
        height = IMAGE_DIMENSIONS
    }.onMouseClick {
        menu.closeScreen()
    } childOf this
    fun setTitle(title: String) = (titleText as UIText).setText(title)
}

class RequisiteMenuFooter(
    menu: IRequisiteMenu,
    previousPageImage: BufferedImage,
    nextPageImage: BufferedImage
) : UIContainer() {
    var previousPage: RequisiteMenuPage? = null
        set(value) {
            println("Setting: $value")
            previousPageButton.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(if (value == null) Color.GRAY else Color.WHITE))
            }

            field = value
        }
    var nextPage: RequisiteMenuPage? = null
        set(value) {
            println("Setting: $value")
            nextPageButton.animate {
                setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(if (value == null) Color.GRAY else Color.WHITE))
            }

            field = value
        }
    private val previousPageButton = UIImage(CompletableFuture.supplyAsync { previousPageImage }).constrain {
        y = CenterConstraint()
        width = IMAGE_DIMENSIONS
        height = IMAGE_DIMENSIONS
    }.onMouseClick {
        println("Interacting: $previousPage")
        if (previousPage != null) {
            menu.initializePage(previousPage!!)
        }
    } childOf this
    private val pageNumberText = UIText().constrain {
        x = CenterConstraint()
        y = CenterConstraint()
    }.setTextScale(2.pixels()) childOf this
    private val nextPageButton = UIImage(CompletableFuture.supplyAsync { nextPageImage }).constrain {
        x = 0.pixels(true)
        y = CenterConstraint()
        width = IMAGE_DIMENSIONS
        height = IMAGE_DIMENSIONS
    }.onMouseClick {
        println("Interacting: $nextPage")
        if (nextPage != null) {
            menu.initializePage(nextPage!!)
        }
    } childOf this
    fun setPageNumber(pageNumber: Int, pageCount: Int) = (pageNumberText as UIText).setText("Page $pageNumber/$pageCount")
}

val IMAGE_DIMENSIONS = 24.pixels()