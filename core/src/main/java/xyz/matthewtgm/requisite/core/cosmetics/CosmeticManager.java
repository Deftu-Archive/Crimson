package xyz.matthewtgm.requisite.core.cosmetics;

import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.networking.packets.impl.cosmetics.CosmeticsRetrievePacket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CosmeticManager {

    private static boolean loaded;
    private static final List<String> requestList = new ArrayList<>();
    private final IRequisite requisite;
    private final RequisiteClientSocket socket;

    private final ICosmeticInitializer initializer;

    public CosmeticManager(IRequisite requisite, ICosmeticInitializer initializer) {
        this.requisite = requisite;
        this.socket = requisite.getManager().getRequisiteSocket();
        this.initializer = initializer;
    }

    public void initialize(String playerUuid) {
        /* Setup all currently available cosmetics. */
        setup();

        /* Version independent layer adding. */
        initializer.initialize(this, playerUuid);

        /* Fetch the player's cosmetics. */
        fetch(playerUuid);
        /* Tell other mods and internal services that we loaded. */
        loaded = true;
    }

    private void setup() {

    }

    public void fetch(String uuid) {
        socket.send(new CosmeticsRetrievePacket(uuid));
    }

    public void loop(Consumer cosmeticConsumer) {

    }

    public static boolean isLoaded() {
        return loaded;
    }

    public static List<String> getRequestList() {
        return requestList;
    }

}