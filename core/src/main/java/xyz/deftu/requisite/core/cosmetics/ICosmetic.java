package xyz.deftu.requisite.core.cosmetics;

public interface ICosmetic<T> {
    void render(T player, float partialTicks);
    CosmeticData data();
}