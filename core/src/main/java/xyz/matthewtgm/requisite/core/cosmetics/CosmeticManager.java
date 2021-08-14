package xyz.matthewtgm.requisite.core.cosmetics;

import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.networking.packets.impl.cosmetics.CosmeticsRetrievePacket;

import java.util.ArrayList;
import java.util.List;

public class CosmeticManager {

    private static boolean loaded;
    private static final List<String> requestList = new ArrayList<>();
    private final IRequisite requisite;
    private final List<BaseCosmetic> cosmetics = new ArrayList<>();
    private final RequisiteClientSocket socket;

    public CosmeticManager(IRequisite requisite) {
        this.requisite = requisite;
        this.socket = requisite.getManager().getRequisiteSocket();
    }

    public void initialize(String playerUuid) {
        for (BaseCosmetic cosmetic : cosmetics) {

        }

        fetch(playerUuid);
        loaded = true;
    }

    public void fetch(String uuid) {
        socket.send(new CosmeticsRetrievePacket(uuid));
    }

    public static boolean isLoaded() {
        return loaded;
    }

    public static List<String> getRequestList() {
        return requestList;
    }

}