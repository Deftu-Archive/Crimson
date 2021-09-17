package xyz.qalcyo.requisite.core.cosmetics;

import java.util.List;

public interface ICosmeticInitializer<T> {
    void initialize(List<ICosmetic<T>> cosmetics, CosmeticData data);
    void finish(CosmeticManager<T> cosmeticManager, List<ICosmetic<T>> cosmetics);
    ICosmeticRenderer<T> renderer();
}