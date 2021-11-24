/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core.networking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.parser.JsonParser;
import xyz.qalcyo.mango.Maps;
import xyz.qalcyo.mango.Multithreading;
import xyz.qalcyo.crimson.core.CrimsonAPI;
import xyz.qalcyo.crimson.core.networking.packets.GreetingPacket;
import xyz.qalcyo.crimson.core.networking.packets.user.UserCountPacket;
import xyz.qalcyo.crimson.core.util.ChatColour;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CrimsonClientSocket extends WebSocketClient {

    private final CrimsonAPI crimson;
    private final Logger logger;

    private final Map<String, Class<? extends BasePacket>> packetRegistry;

    private UUID sessionId;
    private long userCount;

    private int failedConnectionCount;

    private long lastRefresh;

    public CrimsonClientSocket(CrimsonAPI crimson) {
        super(crimson.retrieveSocketUri(), new Draft_6455());
        this.crimson = crimson;
        this.logger = LogManager.getLogger("CrimsonClientSocket");

        this.packetRegistry = Maps.newHashMap();

        /* Settings. */
        setTcpNoDelay(true);
        setReuseAddr(true);
        setConnectionLostTimeout(0);

        initialize();
    }

    /**
     * Connects to the websocket with an await fashion, not allowing the current thread to continue until the websocket has connected or failed.
     * @return Whether the socket was able to connect or not.
     */
    public boolean awaitConnect() {
        try {
            return connectBlocking();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reconnects to the websocket with an await fashion, not allowing the current thread to continue until the websocket has connected or failed.
     *
     * @return Whether the socket was able to connect or not.
     */
    public boolean awaitReconnect() {
        try {
            return reconnectBlocking();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Refreshes the user's connection to Crimson's websocket.
     */
    public void refresh() {
        if (System.currentTimeMillis() - lastRefresh < 300000) {
            logger.error(String.format("Last websocket refresh was less than 5 minutes ago, not permitting., (%s)", lastRefresh));
            return;
        }

        lastRefresh = System.currentTimeMillis();
        crimson.getNotifications().push("Crimson WebSocket", ChatColour.RED + "Refreshing connection...");
        close(WebSocketClose.NORMAL);
        boolean status = awaitReconnect();
        crimson.getNotifications().push("Crimson WebSocket", status ? ChatColour.GREEN + "Successfully refreshed connection." : ChatColour.RED + "Failed to refresh connection.");
    }

    /**
     * Called when the connection to the websocket's server is opened.
     *
     * @param handshake The handshake data sent by the server.
     */
    public void onOpen(ServerHandshake handshake) {
        logger.info(String.format("Opened connection with Crimson's server websocket. (code=%s | message=%s)", handshake.getHttpStatus(), handshake.getHttpStatusMessage()));
        send(new GreetingPacket());
        send(new UserCountPacket());

        lastRefresh = System.currentTimeMillis();
    }

    /**
     * Called when the connection to the websocket's server is killed, ended or closed.
     *
     * @param code The close code.
     * @param reason The reason for the close.
     * @param remote Whether this close was remote or not.
     */
    public void onClose(int code, String reason, boolean remote) {
        onClose(WebSocketClose.fromCode(code), reason, remote);
    }

    /**
     * Called when the connection to the websocket's server is killed, ended or closed.
     *
     * @param code The close code.
     * @param reason The reason for the close.
     * @param remote Whether this close was remote or not.
     */
    private void onClose(WebSocketClose code, String reason, boolean remote) {
        logger.error(String.format("Closed connection with Crimson's server socket. (code=%s | reason=%s)", code, reason));
        if (code != WebSocketClose.NORMAL) {
            failedConnectionCount++;
            if (failedConnectionCount < 3 && crimson.getNotifications() != null) {
                Multithreading.schedule(this::awaitReconnect, 15, TimeUnit.SECONDS);
            } else {
                if (crimson.getNotifications() != null) {
                    crimson.getNotifications().push("Networking error!", "The connection to Crimson's network was disconnected abnormally. Click to attempt to reconnect.", instance -> {
                        awaitReconnect();
                    });
                }
            }
        } else {
            failedConnectionCount = 0;
        }
    }

    /**
     * Called when the connection is sent a `String` type message by the server.
     *
     * @param message The message received.
     */
    public void onMessage(String message) {
        if (message != null && !message.isEmpty()) {
            onMessage(StandardCharsets.UTF_8.encode(message));
        }
    }

    /**
     * Called when the connection is sent message by the server.
     *
     * @param message The message received.
     */
    public void onMessage(ByteBuffer message) {
        String str = StandardCharsets.UTF_8.decode(message).toString();
        if (!str.isEmpty()) {
            JsonElement json = JsonParser.parse(str);
            if (json.isJsonObject()) {
                JsonObject object = json.getAsJsonObject();
                if (!object.isEmpty() && object.hasKey("type") && object.hasKey("data")) {
                    Class<? extends BasePacket> packet = packetRegistry.get(object.getAsString("type"));
                    if (packet != null) {
                        try {
                            BasePacket instance = packet.getDeclaredConstructor().newInstance();
                            instance.receive(this, object, object.getAsObject("data"));
                        } catch (Exception e) {
                            e.printStackTrace();
                            crimson.getBridge().getSocketBridge().chat(ChatColour.RED + "An unexpected error occurred while handling a Crimson packet.\n" + e);
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
    public void onError(Exception ex) {
        logger.error("An unexpected error has occurred.", ex);
        crimson.getBridge().getSocketBridge().chat(Arrays.toString(ex.getStackTrace()));
    }

    /**
     * Closes the connection to the server.
     *
     * @param code The code to provide.
     */
    public void close(WebSocketClose code) {
        close(code.getCode());
    }

    /**
     * Closes the connection to the server.
     *
     * @param code The code to provide.
     * @param message The message to provide with this closure.
     */
    public void close(WebSocketClose code, String message) {
        close(code.getCode(), message);
    }

    /**
     * Sends a new packet to the server.
     *
     * @param packet The packet to send.
     */
    public void send(BasePacket packet) {
        try {
            packet.send(this, packet.getData());
            if (isOpen()) {
                send(packet.jsonify().getAsString().getBytes(StandardCharsets.UTF_8));
            } else {
                logger.error("Tried to send " + packet.getType() + " but connection wasn't open!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            crimson.getBridge().getSocketBridge().chat(ChatColour.RED + "An unexpected error occurred while handling a Crimson packet.\n" + e);
        }
    }

    /**
     * Initializes packets.
     */
    private void initialize() {
        register("GREETING", GreetingPacket.class);
        register("USER_COUNT", UserCountPacket.class);
    }

    /**
     * Registers a new packet under the ID provided.
     *
     * @param id The ID of the new packet.
     * @param clazz The class of the new packet.
     */
    public void register(String id, Class<? extends BasePacket> clazz) {
        packetRegistry.put(id, clazz);
    }

    /**
     * Updates this game session's ID.
     *
     * @param sessionId The new session ID for this session.
     */
    public void updateSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Updates the Crimson user count stored locally.
     *
     * @param userCount The new user count.
     */
    public void updateUserCount(long userCount) {
        this.userCount = userCount;
    }

    public CrimsonAPI getCrimson() {
        return crimson;
    }

    public Logger getLogger() {
        return logger;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public long getUserCount() {
        return userCount;
    }

    public int getFailedConnectionCount() {
        return failedConnectionCount;
    }

    public long getLastRefresh() {
        return lastRefresh;
    }

    public boolean isRefreshAvailable() {
        return System.currentTimeMillis() - lastRefresh > 300000;
    }

}