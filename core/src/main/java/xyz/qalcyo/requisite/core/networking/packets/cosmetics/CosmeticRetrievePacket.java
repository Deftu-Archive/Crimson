package xyz.qalcyo.requisite.core.networking.packets.cosmetics;

import xyz.qalcyo.json.entities.JsonArray;
import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.requisite.core.cosmetics.CosmeticData;
import xyz.qalcyo.requisite.core.cosmetics.PlayerCosmeticData;
import xyz.qalcyo.requisite.core.networking.BasePacket;
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket;

import java.util.List;

public class CosmeticRetrievePacket extends BasePacket {

    private final String uuid;

    public CosmeticRetrievePacket(String uuid) {
        super("COSMETIC_RETRIEVE");
        this.uuid = uuid;
    }

    public CosmeticRetrievePacket() {
        this(null);
    }

    public void send(RequisiteClientSocket socket, JsonObject data) {
        data.add("uuid", uuid);
    }

    public void receive(RequisiteClientSocket socket, JsonObject packet, JsonObject data) {
        if (data.hasKey("uuid") && data.hasKey("owned") && data.hasKey("enabled")) {
            JsonElement ownedElement = data.get("owned");
            JsonElement enabledElement = data.get("enabled");
            if (ownedElement.isJsonArray() && enabledElement.isJsonArray()) {
                JsonArray owned = ownedElement.getAsJsonArray();
                JsonArray enabled = enabledElement.getAsJsonArray();

                List<CosmeticData> ownedCosmetics = Lists.newArrayList();
                List<CosmeticData> enabledCosmetics = Lists.newArrayList();

                for (JsonElement cosmetic : owned) {
                    CosmeticData retrieved = socket.getRequisite().getCosmeticManager().fromId(cosmetic.getAsString());
                    if (retrieved != null) {
                        ownedCosmetics.add(retrieved);
                    }
                }

                for (JsonElement cosmetic : enabled) {
                    CosmeticData retrieved = socket.getRequisite().getCosmeticManager().fromId(cosmetic.getAsString());
                    if (retrieved != null) {
                        enabledCosmetics.add(retrieved);
                    }
                }

                socket.getRequisite().getCosmeticManager().getPlayerData().put(data.getAsString("uuid"), new PlayerCosmeticData(ownedCosmetics, enabledCosmetics));
            }
        }
    }

}