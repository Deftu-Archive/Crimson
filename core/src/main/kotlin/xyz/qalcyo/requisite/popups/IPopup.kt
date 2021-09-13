package xyz.qalcyo.requisite.popups

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIRoundedRectangle
import gg.essential.elementa.components.Window
import xyz.qalcyo.requisite.core.data.IScreenPosition

interface IPopup {
    val id: String

    fun render(position: IScreenPosition?, partialTicks: Float)
    var title: String

    val window: Window
    val content: UIRoundedRectangle
    val visible: UIComponent
}
