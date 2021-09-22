package xyz.qalcyo.requisite.core.cosmetics;

public interface ICosmetic<T> {
    void render(T player, float partialTicks);
    default void render(CosmeticManager<T> cosmeticManager, T player, float partialTicks) {
        if (cosmeticManager.hasCosmetic(player, this)) {
            render(player, partialTicks);
        }
    }
    CosmeticData data();
}