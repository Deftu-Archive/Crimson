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
package xyz.qalcyo.requisite.core.hypixel.locraw

import xyz.qalcyo.json.parser.JsonParser
import xyz.qalcyo.json.util.JsonHelper
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.events.ChatMessageReceivedEvent
import xyz.qalcyo.requisite.core.events.WorldLoadEvent
import xyz.qalcyo.requisite.core.hypixel.HypixelHelper
import xyz.qalcyo.requisite.core.hypixel.events.LocrawReceivedEvent
import java.util.concurrent.TimeUnit

class HypixelLocrawManager(private val requisite: IRequisite, hypixelManager: HypixelHelper) {

    private val hypixelManager: HypixelHelper
    var locraw: HypixelLocraw? = null
        private set
    private var allowCancel = false
    protected fun onWorldLoad(event: WorldLoadEvent?) {
        locraw = null
        allowCancel = false
        if (hypixelManager.isOnHypixel) {
            enqueueUpdate(1000)
        }
    }

    private fun onChatMessageReceived(event: ChatMessageReceivedEvent) {
        val stripped = requisite.stringHelper.removeFormattingCodes(event.message)
        if (JsonHelper.isValidJson(stripped)) {
            val parsed = JsonParser.parse(stripped)
            if (parsed.isJsonObject) {
                val `object` = parsed.asJsonObject
                if (`object`.hasKey("server") && `object`.getAsString("server").contains("limbo")) {
                    forceUpdate(HypixelLocraw.LIMBO)
                    event.cancel()
                }
                forceUpdate(
                    HypixelLocraw(
                        `object`["server"],
                        `object`["mode"],
                        `object`["map"],
                        `object`["gametype"]
                    )
                )
                event.cancel()
            }
        }
    }

    fun enqueueUpdate(interval: Int) {
        if (!allowCancel) {
            allowCancel = true
            requisite.multithreading.schedule(
                { requisite.messageQueue.queue("/locraw") },
                interval.toLong(),
                TimeUnit.MILLISECONDS
            )
        }
    }

    private fun forceUpdate(locraw: HypixelLocraw) {
        this.locraw = locraw
        requisite.eventBus.call(LocrawReceivedEvent(locraw))
        allowCancel = false
    }

    init {
        this.hypixelManager = hypixelManager
        requisite.eventBus.register(WorldLoadEvent::class.java) {
                event: WorldLoadEvent? -> onWorldLoad(event)
        }
        requisite.eventBus.register(ChatMessageReceivedEvent::class.java) { event: ChatMessageReceivedEvent ->
            onChatMessageReceived(event)
        }
    }

}