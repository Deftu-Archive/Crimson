package xyz.qalcyo.requisite.popups

import xyz.qalcyo.requisite.core.IRequisite

interface IFixedPopup : IPopup {
    val requisite: IRequisite
    fun render(partialTicks: Float) {
        render(requisite.positionHelper.createCenteredPosition(), partialTicks)
    }
}