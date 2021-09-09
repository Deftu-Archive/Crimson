package xyz.deftu.requisite.core.cosmetics;

import xyz.deftu.mango.Lists;

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
        cosmeticData.add(CosmeticData.from("Beehive Cloak", CosmeticType.CLOAK));
        //cosmeticData.add(CosmeticData.from("Booster Cloak", CosmeticType.CLOAK));
        //cosmeticData.add(CosmeticData.from("Bug Hunter Cloak", CosmeticType.CLOAK));

        for (CosmeticData cosmeticData : cosmeticData) {
            cosmeticInitializer.initialize(cosmetics, cosmeticData);
        }

        cosmeticInitializer.finish(cosmetics);
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