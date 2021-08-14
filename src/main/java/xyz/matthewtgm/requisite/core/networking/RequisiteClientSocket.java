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

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class RequisiteClientSocket extends WebSocketClient {

    private final RequisitePacketHandler packetHandler;
    private final IRequisite requisite;

    public RequisiteClientSocket(URI serverUri, IRequisite requisite) {
        super(serverUri, new Draft_6455());
        this.requisite = requisite;
        this.packetHandler = new RequisitePacketHandler(this);
    }

    public void connect() {
        super.connect();
    }

    public void reconnect() {
        new Thread(super::reconnect).start();
    }

    public void onOpen(ServerHandshake handshake) {}

    /**
     * This is required for some reason..?
     */
    public void onMessage(String message) {
        packetHandler.handle(StandardCharsets.UTF_8.encode(message));
    }

    /**
     * Invoked when a message from the server is sent. The parameter value is parsed using the {@link RequisitePacketHandler}
     */
    public void onMessage(ByteBuffer bytes) {
        packetHandler.handle(bytes);
    }

    public void onClose(int code, String reason, boolean remote) {}

    public void onError(Exception ex) {}

    public void send(BasePacket packet) {
        packetHandler.send(packet);
    }

    public IRequisite getRequisite() {
        return requisite;
    }

}