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
import xyz.qalcyo.requisite.core.networking.packets.cosmetics.CosmeticRetrievePacket;
import xyz.qalcyo.requisite.core.util.ChatColour;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RequisiteClientSocket extends WebSocketClient {

    private final IRequisite requisite;
    private final Logger logger;
    private final ISocketHelper helper;

    private final Map<String, Class<? extends BasePacket>> packetRegistry;

    public RequisiteClientSocket(IRequisite requisite, ISocketHelper helper) {
        super(requisite.fetchSocketUri(), new Draft_6455());
        this.requisite = requisite;
        this.logger = LogManager.getLogger("RequisiteClientSocket");
        this.helper = helper;

        this.packetRegistry = Maps.newHashMap();

        /* Settings. */
        setTcpNoDelay(true);
        setReuseAddr(true);
        setConnectionLostTimeout(120);

        initialize();
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
    }

    /**
     * Called when the connection to the websocket's server is killed, ended or closed.
     *
     * @param code The close code.
     * @param reason The reason for the close.
     * @param remote Whether this close was remote or not.
     */
    public void onClose(int code, String reason, boolean remote) {
        logger.error(String.format("Closed connection with Requisite's server websocket. (code=%s | reason=%s)", code, reason));

        requisite.getNotifications().push("Error!", "Failed to connect to Requisite WebSocket. " + ChatColour.BOLD + "Click to try a reconnect.", notification -> {
            boolean socketReconnected = awaitReconnect();
            if (!socketReconnected) {
                requisite.getNotifications().push(notification.clone());
                notification.close();
            }
        });
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
                            helper.chat(ChatColour.RED + "An unexpected error occurred while handling a Requisite packet.\n" + e);
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
        helper.chat(Arrays.toString(ex.getStackTrace()));
    }

    public void send(BasePacket packet) {
        try {
            packet.send(this, packet.getData());
            if (isOpen()) {
                send(packet.jsonify().getAsString());
            } else {
                logger.error("Tried to send " + packet.getType() + " but connection wasn't open!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            helper.chat(ChatColour.RED + "An unexpected error occurred while handling a Requisite packet.\n" + e);
        }
    }

    /**
     * Initializes packets.
     */
    private void initialize() {
        packetRegistry.put("COSMETIC_RETRIEVE", CosmeticRetrievePacket.class);
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

}