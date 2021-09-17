package xyz.qalcyo.requisite.core.cosmetics;

public interface ICosmeticHelper<T> {
    void initialize(CosmeticManager<T> cosmeticManager);
    String getUuid();
    boolean hasCosmetic(T player, ICosmetic<T> cosmetic);
}