package xyz.qalcyo.requisite.popups

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIRoundedRectangle
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.Window
import gg.essential.elementa.dsl.childOf
import gg.essential.universal.UMatrixStack
import net.minecraft.client.Minecraft
import java.awt.Color

class LockedPopup(private val id: String, override var title: String, val x: Float, val y: Float, val width: Float, val height: Float, val radius: Float, shadow: Boolean):
    ILockedPopup {

    fun initialize() {
        PopupManager.addLockedPopup(id, this)
    }

    override fun drawPopup(matrixStack: UMatrixStack) {
        println("hi!")
        if (Minecraft.getMinecraft().fontRendererObj != null) {
            window.drawCompat(matrixStack)
        }
    }
    override val window: Window = Window(10)
    override val popup: UIRoundedRectangle = object : UIRoundedRectangle(radius) {
        override fun getLeft(): Float {
            return x
        }

        override fun getRight(): Float {
            return x + width
        }

        override fun getTop(): Float {
            return y
        }

        override fun getBottom(): Float {
            return y + height
        }
    } childOf window

    val text: UIText = object : UIText(title, shadow) {
        override fun getLeft(): Float {
            return x + radius
        }

        override fun getRight(): Float {
            return getLeft() - radius
        }

        override fun getTop(): Float {
            return y
        }


    } childOf popup

    val closeButton: UIBlock = object : UIBlock(Color.RED) {
        override fun getLeft(): Float {
            return x + width - 10
        }

        override fun getRight(): Float {
            return x + width
        }

        override fun getTop(): Float {
            return y
        }

        override fun getBottom(): Float {
            return y + 10
        }
    }.onMouseClick {
        PopupManager.removeLockedPopup(id)
    } as UIBlock childOf popup

    val textLine: UIBlock = object : UIBlock(Color.BLACK) {
        override fun getLeft(): Float {
            return text.getBottom() + 5
        }

        override fun getRight(): Float {
            return x + width
        }

        override fun getTop(): Float {
            return y + radius
        }

        override fun getBottom(): Float {
            return getTop() + 3
        }
    } childOf popup

}