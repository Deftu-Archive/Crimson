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

package xyz.qalcyo.requisite.core

import xyz.qalcyo.mango.collections.Pair
import xyz.qalcyo.mango.collections.impl.ImmutablePair
import xyz.qalcyo.requisite.core.events.*
import xyz.qalcyo.simpleeventbus.Event

class RequisiteEventManager(private val requisite: IRequisite) {

    fun handleHudRender(partialTicks: Float) {
        requisite.eventBus.call(RenderHudEvent(partialTicks))
    }

    fun handleTick() {
        requisite.eventBus.call(TickEvent())
    }

    fun handleRenderTick(partialTicks: Float) {
        requisite.eventBus.call(RenderTickEvent(partialTicks))
    }

    fun handleChatMessageSent(message: String?): Pair<String, Boolean> {
        val event = SendChatMessageEvent(message)
        requisite.eventBus.call(event)
        return ImmutablePair(event.message, event.isCancelled)
    }

    fun handleChatMessageReceived(message: String?, type: Byte): Boolean {
        return parseCancellable(ChatMessageReceivedEvent(message, type))
    }

    fun handleKeyInput(keyCode: Int, down: Boolean, repeated: Boolean): Boolean {
        return parseCancellable(KeyInputEvent(keyCode, down, repeated))
    }

    fun handleWorldLoad() {
        requisite.eventBus.call(WorldLoadEvent())
    }

    private fun parseCancellable(event: Event): Boolean {
        requisite.eventBus.call(event)
        return event.isCancellable && event.isCancelled
    }

}