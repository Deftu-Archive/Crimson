package xyz.qalcyo.requisite.core.cosmetics

interface ICosmeticRenderer<T> {
    fun initialize(cosmeticManager: CosmeticManager<T>?, cosmetics: List<ICosmetic<T>?>?)
}