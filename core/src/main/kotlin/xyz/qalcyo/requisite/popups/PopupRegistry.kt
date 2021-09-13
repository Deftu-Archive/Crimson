package xyz.qalcyo.requisite.popups

import xyz.qalcyo.mango.collections.Pair
import xyz.qalcyo.mango.collections.impl.ImmutablePair
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.events.RenderTickEvent

class PopupRegistry {

    val popupList: MutableList<Pair<String, IPopup>> = mutableListOf()

    fun add(popup: IPopup) {
        if (popupList.stream().anyMatch { it.first().equals(popup.id) }) {
            throw IllegalArgumentException("Popup with that ID is already open!")
        }

        popupList += ImmutablePair(popup.id, popup)
    }

    fun remove(id: String) {
        if (popupList.isNotEmpty()) {
            popupList.removeIf { it.first().equals(id) }
        }
    }

    fun initialize(requisite: IRequisite) {
        requisite.eventBus.register(RenderTickEvent::class.java, this::render)
    }

    fun render(event: RenderTickEvent) {
        for (pair in popupList) {
            val popup = pair.second()

            popup.render(null, event.partialTicks)
        }
    }

}