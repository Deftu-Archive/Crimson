package xyz.qalcyo.requisite.core.networking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.parser.JsonParser;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.mango.Maps;
import xyz.qalcyo.requisite.core.IRequisite;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RequisiteClientSocket extends WebSocketClient {

    private final IRequisite requisite;
    private final Logger logger;
    private final ISocketHelper helper;

    private final List<ISocketHandler> socketHandlers;
    private final Map<String, IPacketHandler> packetHandlers;

    public RequisiteClientSocket(IRequisite requisite, ISocketHelper helper) {
        super(requisite.fetchSocketUri(), new Draft_6455());
        this.requisite = requisite;
        this.logger = LogManager.getLogger("RequisiteClientSocket");
        this.helper = helper;

        this.socketHandlers = Lists.newArrayList();
        this.packetHandlers = Maps.newHashMap();

        /* Settings. */
        setTcpNoDelay(true);
        setReuseAddr(true);
        setConnectionLostTimeout(120);
    }

    /**
     * Connects to the websocket with an await fashion, not allowing the current thread to continue until the websocket has connected or failed.
     *
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
     * Called when the connection to the websocket's server is opened.
     *
     * @param handshake The handshake data sent by the server.
     */
    public void onOpen(ServerHandshake handshake) {
        logger.info(String.format("Opened connection with Requisite's server websocket. (code=%s | message=%s)", handshake.getHttpStatus(), handshake.getHttpStatusMessage()));
        initialize();
        for (ISocketHandler socketHandler : socketHandlers) {
            socketHandler.connect(this);
        }
    }

    /**
     * Called when the connection to the websocket's server is killed, ended or closed.
     *
     * @param code The close code.
     * @param reason The reason for the close.
     * @param remote Whether this close was remote or not.
     */
    public void onClose(int code, String reason, boolean remote) {
        logger.error("Closed connection with Requisite's server websocket. (code={} | reason={})", code, reason);
        for (ISocketHandler socketHandler : socketHandlers) {
            socketHandler.disconnect(this);
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
                    packetHandlers.get(object.getAsString("type")).handle(object);
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
        helper.chat(Arrays.toString(ex.getStackTrace()));
    }

    /**
     * Called when the websocket connection is opened.
     */
    public void initialize() {
        for (ISocketHandler socketHandler : socketHandlers) {
            socketHandler.initialize(this);
        }
    }

    /**
     * Adds a socket handler to the socket handler registry list.
     *
     * @param handler The handler to add.
     */
    public void registerSocketHandler(ISocketHandler handler) {
        socketHandlers.add(handler);
    }

    /**
     * Adds a packet handler to the packet handler registry map.
     *
     * @param type The packet name/type to handle.
     * @param handler The handler to use.
     */
    public void registerPacketHandler(String type, IPacketHandler handler) {
        packetHandlers.put(type, handler);
    }

    public IRequisite getRequisite() {
        return requisite;
    }

    public Logger getLogger() {
        return logger;
    }

    public ISocketHelper getHelper() {
        return helper;
    }

    public List<ISocketHandler> getSocketHandlers() {
        return socketHandlers;
    }

    public Map<String, IPacketHandler> getPacketHandlers() {
        return packetHandlers;
    }

}