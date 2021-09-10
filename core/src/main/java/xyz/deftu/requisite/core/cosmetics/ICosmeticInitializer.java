package xyz.deftu.requisite.core.cosmetics;

import java.util.List;

public interface ICosmeticInitializer<T> {
    void initialize(List<ICosmetic<T>> cosmetics, CosmeticData data);
    void finish(List<ICosmetic<T>> cosmetics);
    ICosmeticRenderer<T> renderer();
}