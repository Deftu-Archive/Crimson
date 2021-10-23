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

import gg.essential.elementa.UIComponent
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.universal.GuiScale
import net.minecraft.client.Minecraft
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.RequisitePalette
import xyz.qalcyo.requisite.core.gui.components.builders.ButtonBuilder
import xyz.qalcyo.requisite.core.gui.main.RequisiteMenuPage
import xyz.qalcyo.requisite.core.gui.main.impl.RequisiteControlsPage
import xyz.qalcyo.requisite.core.gui.main.impl.RequisiteMainPage
import xyz.qalcyo.requisite.core.integration.mods.IMod
import xyz.qalcyo.requisite.core.integration.mods.IModConfigurationMenu
import xyz.qalcyo.requisite.core.util.ChatColour
import java.awt.Color
import java.util.concurrent.CompletableFuture

class RequisiteMenu : WindowScreen(
    restoreCurrentGuiOnClose = true,
    newGuiScale = GuiScale.scaleForScreenSize().ordinal
), IModConfigurationMenu {

    private var availablePages: Array<RequisiteMenuPage>? = null
    private var currentPage: RequisiteMenuPage? = null

    private var title: UIText? = null
    private val currentContent = UIContainer()

    init {
        availablePages = arrayOf(RequisiteMainPage(), RequisiteControlsPage())
        currentPage = availablePages!![0]
        title = UIText(currentPage!!.title)

        val pageListContainer = UIContainer().constrain {
            x = 0.pixels()
            y = 0.pixels()
            width = ChildBasedSizeConstraint(1f)
            height = RelativeConstraint()
        } childOf window
        for (availablePage in availablePages!!) {
            val pageButton = UIImage(CompletableFuture.supplyAsync { availablePage.icon }).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(1f)
                width = 32.pixels()
                height = 32.pixels()
            }.onMouseClick {
                currentPage = availablePage
                title!!.setText(currentPage!!.title)
                for (child in pageListContainer.children) {
                    child.animate {
                        setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(Color.WHITE))
                    }
                }

                animate {
                    setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(RequisitePalette.getMain().asColor()))
                }

                currentContent.clearChildren()
                currentPage!! childOf currentContent
                currentPage!!.reset()
                currentPage!!.initialize()
            } childOf pageListContainer

            if (currentPage!!.title == availablePage.title) {
                pageButton.animate {
                    setColorAnimation(Animations.OUT_EXP, 1f, ConstantColorConstraint(RequisitePalette.getMain().asColor()))
                }
            }
        }

        val pageDivider = UIBlock(Color.BLACK).constrain {
            x = SiblingConstraint()
            y = 0.pixels()
            width = 2.pixels()
            height = RelativeConstraint()
        } childOf window

        val titleContainer = UIContainer().constrain {
            x = SiblingConstraint()
            y = 0.pixels()
            width = RelativeConstraint()
            height = 50.pixels()
        } childOf window
        title!!.constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        }.setTextScale(2.pixels()) childOf titleContainer

        val titleDivider = UIBlock(Color.BLACK).constrain {
            x = pageDivider.getRight().pixels()
            y = titleContainer.getBottom().pixels()
            width = RelativeConstraint()
            height = 2.pixels()
        } childOf window

        for (availablePage in availablePages!!) {
            availablePage.constrain {
                x = pageDivider.getRight().pixels()
                y = titleDivider.getBottom().pixels()
                width = RelativeConstraint()
                height = RelativeConstraint()
            }
        }

        println("Menu: ${window.getRight()}")
        println("Menu: ${window.getBottom()}")

        currentContent.constrain {
            x = pageDivider.getRight().pixels()
            y = titleDivider.getBottom().pixels()
            width = ChildBasedSizeConstraint()
            height = ChildBasedSizeConstraint()
        } childOf window
    }

    override fun setWorldAndResolution(mc: Minecraft?, width: Int, height: Int) {
        newGuiScale = GuiScale.scaleForScreenSize().ordinal
        super.setWorldAndResolution(mc, width, height)
    }

    override fun open() = Requisite.getInstance().guiHelper.open(this)
    override fun getMod(): IMod = Requisite.getInstance()

}
