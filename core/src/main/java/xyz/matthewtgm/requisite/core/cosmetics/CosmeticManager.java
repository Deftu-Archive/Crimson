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

package xyz.matthewtgm.requisite.core.cosmetics;

import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.cosmetics.rendering.CosmeticLayer;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.networking.packets.impl.cosmetics.CosmeticsRetrievePacket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CosmeticManager {

    private static final List<String> requestList = new ArrayList<>();
    private final List<CosmeticLayer> cosmetics = new ArrayList<>();

    private final RequisiteClientSocket socket;

    private final ICosmeticFinalizer finalizer;

    public CosmeticManager(IRequisite requisite, ICosmeticFinalizer finalizer) {
        this.socket = requisite.getManager().getRequisiteSocket();

        this.finalizer = finalizer;
    }

    public void initialize(String playerUuid) {
        finalizer.initialize(this, cosmetics);

        for (CosmeticLayer cosmetic : cosmetics) {
            finalizer.finalize(cosmetic);
        }

        fetch(playerUuid);
    }

    public CosmeticLayer fromId(String id) {
        AtomicReference<CosmeticLayer> gotten = new AtomicReference<>(null);
        cosmetics.stream().filter(cosmetic -> cosmetic.getId().equals(id)).findFirst().ifPresent(gotten::set);
        return gotten.get();
    }

    public void handleJoin(String uuid) {
        if (requestList.size() > 200)
            requestList.clear();
        fetch(uuid);
    }

    public void tick() {
        for (CosmeticLayer cosmetic : cosmetics) {
           cosmetic.tick();
        }
    }

    public void fetch(String uuid) {
        if (requestList.contains(uuid))
            return;
        socket.send(new CosmeticsRetrievePacket(uuid));
        requestList.add(uuid);
    }

}