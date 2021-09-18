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

package xyz.qalcyo.requisite.core.networking

import gg.essential.universal.ChatColor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.java_websocket.client.WebSocketClient
import org.java_websocket.drafts.Draft_6455
import org.java_websocket.handshake.ServerHandshake
import xyz.qalcyo.json.parser.JsonParser
import xyz.qalcyo.mango.Maps
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.networking.packets.cosmetics.CosmeticRetrievePacket
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.*

class RequisiteClientSocket(
    val requisite: IRequisite,
    val helper: ISocketHelper
) : WebSocketClient(requisite.fetchSocketUri(), Draft_6455()) {

    val logger: Logger = LogManager.getLogger("RequisiteClientSocket")
    private val packetRegistry: MutableMap<String, Class<out BasePacket>> = Maps.newHashMap()

    /**
     * Connects to the websocket with an await fashion, not allowing the current thread to continue until the websocket has connected or failed.
     *
     * @return Whether the socket was able to connect or not.
     */
    fun awaitConnect() =
        try {
            connectBlocking()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    /**
     * Reconnects to the websocket with an await fashion, not allowing the current thread to continue until the websocket has connected or failed.
     *
     * @return Whether the socket was able to connect or not.
     */
    fun awaitReconnect() =
        try {
            reconnectBlocking()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

    /**
     * Called when the connection to the websocket's server is opened.
     *
     * @param handshake The handshake data sent by the server.
     */
    override fun onOpen(handshake: ServerHandshake) {
        logger.info(String.format(
            "Opened connection with Requisite's server websocket. (code=%s | message=%s)",
            handshake.httpStatus,
            handshake.httpStatusMessage
        ))
    }

    /**
     * Called when the connection to the websocket's server is killed, ended or closed.
     *
     * @param code The close code.
     * @param reason The reason for the close.
     * @param remote Whether this close was remote or not.
     */
    override fun onClose(code: Int, reason: String, remote: Boolean) {
        logger.error(String.format(
                "Closed connection with Requisite's server websocket. (code=%s | reason=%s)",
                code,
                reason
        ))

        requisite.notifications.push(
            "Error!",
            "Failed to connect to Requisite WebSocket. ${ChatColor.BOLD}Click to try a reconnect."
        ) { notification ->
            val socketReconnected = awaitReconnect()
            if (!socketReconnected) {
                requisite.notifications.push(notification.clone())
                notification.close()
            }
        }
    }

    /**
     * Called when the connection is sent a `String` type message by the server.
     *
     * @param message The message received.
     */
    override fun onMessage(message: String) {
        if (message.isNotEmpty()) {
            onMessage(StandardCharsets.UTF_8.encode(message))
        }
    }

    /**
     * Called when the connection is sent message by the server.
     *
     * @param message The message received.
     */
    override fun onMessage(message: ByteBuffer) {
        val str = StandardCharsets.UTF_8.decode(message).toString()
        if (str.isNotEmpty()) {
            val json = JsonParser.parse(str)
            if (json.isJsonObject) {
                val `object` = json.asJsonObject
                if (!`object`.isEmpty && `object`.hasKey("type") && `object`.hasKey("data")) {
                    val packet = packetRegistry[`object`.getAsString("type")]
                    if (packet != null) {
                        try {
                            val instance = packet.getDeclaredConstructor().newInstance()
                            instance.receive(this, `object`, `object`.getAsObject("data"))
                        } catch (e: Exception) {
                            e.printStackTrace()
                            helper.chat("${ChatColor.RED}An unexpected error occurred while handling a Requisite packet.\n" + e)
                        }
                    }
                }
            }
        }
    }

    /**
     * Called when an error occurs in the websocket's internals or handlers.
     *
     * @param ex The exception thrown.
     */
    override fun onError(ex: Exception) {
        logger.error("An unexpected error has occurred.", ex)
        helper.chat(Arrays.toString(ex.stackTrace))
    }

    fun send(packet: BasePacket) {
        try {
            packet.send(this, packet.data)
            if (isOpen) {
                send(packet.jsonify().asString)
            } else {
                logger.error("Tried to send " + packet.type + " but connection wasn't open!")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            helper.chat("${ChatColor.RED}An unexpected error occurred while handling a Requisite packet.\n" + e)
        }
    }

    /**
     * Initializes packets.
     */
    private fun initialize() {
        packetRegistry["COSMETIC_RETRIEVE"] = CosmeticRetrievePacket::class.java
    }

    init {
        /* Settings. */
        isTcpNoDelay = true
        isReuseAddr = true
        connectionLostTimeout = 120

        initialize()
    }

}