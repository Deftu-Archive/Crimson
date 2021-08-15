/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.matthewtgm.requisite.core.networking;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.networking.packets.BasePacket;
import xyz.matthewtgm.requisite.core.util.ChatColour;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RequisiteClientSocket extends WebSocketClient {

    private final RequisitePacketHandler packetHandler;
    private final IRequisite requisite;

    private final List<INetworked> networkedList = new ArrayList<>();

    public RequisiteClientSocket(URI serverUri, IRequisite requisite) {
        super(serverUri, new Draft_6455());
        this.requisite = requisite;
        this.packetHandler = new RequisitePacketHandler(this);

        setTcpNoDelay(true);
        setConnectionLostTimeout(120);
    }

    public void connect() {
        super.connect();
    }

    public void reconnect() {
        new Thread(super::reconnect).start();
    }

    /**
     * Called when the connection to the server is opened.
     *
     * @param handshake The handshake sent by the server to ensure that everything worked.
     */
    public void onOpen(ServerHandshake handshake) {
        for (INetworked networked : networkedList)
            networked.connect(this, handshake);
        requisite.getManager().getUniversalLogger().info("Successfully connected to Requisite websocket server.");
    }

    /**
     * This is required... For some reason?
     */
    public void onMessage(String message) {
        packetHandler.handle(StandardCharsets.UTF_8.encode(message));
    }

    /**
     * Invoked when a message from the server is sent. The parameter value is parsed using the {@link RequisitePacketHandler}
     */
    public void onMessage(ByteBuffer bytes) {
        for (INetworked networked : networkedList)
            networked.receive(this, StandardCharsets.UTF_8.decode(bytes).toString());
        packetHandler.handle(bytes);
    }

    /**
     * Called when the client's connection to the server is closed, terminated or disconnected.
     *
     * @param code The code sent by the server.
     * @param reason The reason sent by the server.
     * @param remote Whether this close was remote or not.
     */
    public void onClose(int code, String reason, boolean remote) {
        for (INetworked networked : networkedList)
            networked.disconnect(this, code, reason);
        requisite.getManager().getChatHelper().send(requisite.getChatPrefix(), ChatColour.RED + "Your game was disconnected from the Requisite websocket with the code " + code + " and the reason " + ChatColour.GREEN + "\"" + reason + "\"");
    }

    /**
     * Handles exceptions thrown by the websocket, unused.
     *
     * @param ex The exception to handle.
     */
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    /**
     * Sends a packet to the Requisite server.
     *
     * @param packet The packet to send.
     */
    public void send(BasePacket packet) {
        packetHandler.send(packet);
    }

    /**
     * Adds a new networked object to the socket.
     *
     * @param networked The networked object.
     */
    public void addNetworked(INetworked networked) {
        networkedList.add(networked);
    }

    /**
     * @return An instance of Requisite.
     */
    public IRequisite getRequisite() {
        return requisite;
    }

}