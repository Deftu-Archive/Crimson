package xyz.deftu.requisite.core.cosmetics;

import xyz.deftu.mango.Lists;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class CosmeticManager<T> {

    private final List<CosmeticData> cosmeticData;
    private final List<ICosmetic<T>> cosmetics;

    private final ICosmeticInitializer<T> cosmeticInitializer;

    public CosmeticManager(ICosmeticInitializer<T> cosmeticInitializer) {
        this.cosmeticData = Lists.newLinkedList();
        this.cosmetics = Lists.newLinkedList();

        this.cosmeticInitializer = cosmeticInitializer;
    }

    public void initialize() {
        //cosmeticData.add(CosmeticData.from("Beehive Cloak", CosmeticType.CLOAK, texture("cloak/beehive_cloak.png")));
        //cosmeticData.add(CosmeticData.from("Booster Cloak", CosmeticType.CLOAK, texture("cloak/booster_cloak.png")));
        cosmeticData.add(CosmeticData.from("Bug Hunter Cloak", CosmeticType.CLOAK, texture("cloak/bug_hunter_cloak.png")));

        for (CosmeticData cosmeticData : cosmeticData) {
            cosmeticInitializer.initialize(cosmetics, cosmeticData);
        }

        cosmeticInitializer.finish(cosmetics);
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

}