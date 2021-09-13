package xyz.qalcyo.requisite.popups

object PopupManager: IPopupManager {
    override val listPopups: MutableList<Pair<String, ILockedPopup>> = mutableListOf()

    override fun addLockedPopup(id: String, popup: ILockedPopup) {
        listPopups.stream().forEach {
            if (it.first == id) {
                throw Exception("The ID of this popup already exists!")
            }
        }
        listPopups.add(Pair(id, popup))
    }

    override fun removeLockedPopup(id: String) {
        if (listPopups.isNotEmpty()) {
            listPopups.removeIf {
                it.first == id
            }
        }
    }

}