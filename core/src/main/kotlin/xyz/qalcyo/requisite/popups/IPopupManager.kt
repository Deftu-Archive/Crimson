package xyz.qalcyo.requisite.popups

interface IPopupManager {
    val listPopups: MutableList<Pair<String, ILockedPopup>>
    fun addLockedPopup(id: String, popup: ILockedPopup)
    fun removeLockedPopup(id: String)
}