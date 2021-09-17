package xyz.qalcyo.requisite.core.cosmetics;

import xyz.qalcyo.mango.Lists;
import xyz.qalcyo.mango.Maps;
import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.networking.packets.cosmetics.CosmeticRetrievePacket;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CosmeticManager<T> {

    private final Map<String, PlayerCosmeticData> playerData;

    private final List<CosmeticData> cosmeticData;
    private final List<ICosmetic<T>> cosmetics;

    private final ICosmeticInitializer<T> cosmeticInitializer;
    private final ICosmeticHelper<T> cosmeticHelper;

    public CosmeticManager(IRequisite requisite, ICosmeticInitializer<T> cosmeticInitializer, ICosmeticHelper<T> cosmeticHelper) {
        this.playerData = Maps.newHashMap();
        this.cosmeticData = Lists.newLinkedList();
        this.cosmetics = Lists.newLinkedList();

        this.cosmeticInitializer = cosmeticInitializer;
        this.cosmeticHelper = cosmeticHelper;

        requisite.getRequisiteSocket().send(new CosmeticRetrievePacket(cosmeticHelper.getUuid()));
    }

    public void initialize() {
        cosmeticHelper.initialize(this);

        /* Public cosmetics. */
        cosmeticData.add(CosmeticData.from("Beehive Cloak", CosmeticType.CLOAK, texture("cloak/beehive_cloak.png")));
        cosmeticData.add(CosmeticData.from("Booster Cloak", CosmeticType.CLOAK, texture("cloak/booster_cloak.png")));
        cosmeticData.add(CosmeticData.from("Bug Hunter Cloak", CosmeticType.CLOAK, texture("cloak/bug_hunter_cloak.png")));
        cosmeticData.add(CosmeticData.from("Developer Cloak", CosmeticType.CLOAK, texture("cloak/developer_cloak.png")));
        cosmeticData.add(CosmeticData.from("Dragon's Eye Cloak", CosmeticType.CLOAK, texture("cloak/dragon_eye_cloak.png")));
        cosmeticData.add(CosmeticData.from("Enchanter Cloak", CosmeticType.CLOAK, texture("cloak/enchanter_cloak.gif"), CosmeticFlag.ANIMATED));
        cosmeticData.add(CosmeticData.from("Keyswitch Cloak", CosmeticType.CLOAK, texture("cloak/keyswitch_cloak.png")));
        cosmeticData.add(CosmeticData.from("Modder Cloak", CosmeticType.CLOAK, texture("cloak/modder_cloak.png")));
        cosmeticData.add(CosmeticData.from("Partner Cloak", CosmeticType.CLOAK, texture("cloak/partner_cloak.png")));
        cosmeticData.add(CosmeticData.from("Vaporwave Cloak", CosmeticType.CLOAK, texture("cloak/vaporwave_cloak.gif"), CosmeticFlag.ANIMATED));
        //cosmeticData.add(CosmeticData.from("Icy Cave Cloak", CosmeticType.CLOAK, texture("cloak/icy_cave_cloak.png")));
        cosmeticData.add(CosmeticData.from("Space Rats Cloak", CosmeticType.CLOAK, texture("cloak/space_rats_cloak.png")));

        /* Exclusive cosmetics. */
        cosmeticData.add(CosmeticData.from("Strebbypatty Cloak", CosmeticType.CLOAK, texture("cloak/exclusive/streb_cloak.png")));

        for (CosmeticData cosmeticData : cosmeticData) {
            cosmeticInitializer.initialize(cosmetics, cosmeticData);
        }

        cosmeticInitializer.finish(this, cosmetics);
    }

    public boolean hasCosmetic(T player, ICosmetic<T> cosmetic) {
        return cosmeticHelper.hasCosmetic(player, cosmetic);
    }

    public CosmeticData fromId(String id) {
        CosmeticData value = null;
        for (CosmeticData data : cosmeticData) {
            if (data.isId(id)) {
                value = data;
                break;
            }
        }
        return value;
    }

    private CosmeticTexture texture(String path) {
        return new CosmeticTexture(path, CosmeticManager.class.getResourceAsStream("/cosmetics/" + path));
    }

    public List<CosmeticData> getCosmeticData() {
        return Collections.unmodifiableList(cosmeticData);
    }

    public List<ICosmetic<T>> getCosmetics() {
        return Collections.unmodifiableList(cosmetics);
    }

    public ICosmeticInitializer<T> getCosmeticInitializer() {
        return cosmeticInitializer;
    }

    public Map<String, PlayerCosmeticData> getPlayerData() {
        return playerData;
    }

}