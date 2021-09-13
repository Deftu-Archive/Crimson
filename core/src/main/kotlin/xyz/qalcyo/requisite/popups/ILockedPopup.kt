package xyz.qalcyo.requisite.popups

import gg.essential.elementa.components.UIRoundedRectangle
import gg.essential.elementa.components.Window
import gg.essential.universal.UMatrixStack

interface ILockedPopup {
    fun drawPopup(matrixStack: UMatrixStack)
    var title: String
    val window: Window
    val popup: UIRoundedRectangle
}