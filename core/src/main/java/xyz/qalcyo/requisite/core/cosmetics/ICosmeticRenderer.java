package xyz.qalcyo.requisite.core.cosmetics;

import java.util.List;

public interface ICosmeticRenderer<T> {
    void initialize(CosmeticManager<T> cosmeticManager, List<ICosmetic<T>> cosmetics);
}