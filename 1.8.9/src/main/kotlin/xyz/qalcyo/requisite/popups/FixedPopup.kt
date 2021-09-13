package xyz.qalcyo.requisite.popups

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIRoundedRectangle
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.Window
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.*
import gg.essential.universal.UMatrixStack
import net.minecraft.client.Minecraft
import xyz.qalcyo.requisite.Requisite
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.data.IScreenPosition

class FixedPopup(
    override val id: String,
    override var title: String,
    override val visible: UIComponent,
    val width: Float,
    val height: Float
) : IFixedPopup {

    override val requisite: IRequisite
        get() = Requisite.getInstance()
    private var position: IScreenPosition? = null

    override fun render(position: IScreenPosition?, partialTicks: Float) {
        this.position = position

        if (Minecraft.getMinecraft().fontRendererObj != null) {
            window.drawCompat(UMatrixStack())
        }
    }

    override val window = Window(12).constrain {
        x = if (position == null) 0.pixels() else position!!.x.pixels()
        x = if (position == null) 0.pixels() else position!!.y.pixels()
        width = this@FixedPopup.width.pixels()
        height = this@FixedPopup.height.pixels()
    }
    override val content = UIRoundedRectangle(3f).constrain {
        x = CenterConstraint()
        x =  CenterConstraint()
        width = this@FixedPopup.width.pixels()
        height = this@FixedPopup.height.pixels()
    } childOf window

    val text = UIText(title).constrain {
        x = 2.pixels()
        y = 2.pixels()
    } childOf content

    val closeButton = UIBlock().constrain {
        x = 2.pixels(true)
        y = 2.pixels(true)
    } childOf content

    init {
        visible childOf content
        visible.onMouseEnter {
            println("Mouse entered visible content.")
        }
        println(visible.parent)
    }

}