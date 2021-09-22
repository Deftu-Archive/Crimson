package xyz.qalcyo.requisite.core.cosmetics

interface ICosmeticInitializer<T> {
    fun initialize(cosmetics: List<ICosmetic<T>?>?, data: CosmeticData?)
    fun finish(cosmeticManager: CosmeticManager<T>?, cosmetics: List<ICosmetic<T>?>?)
    fun renderer(): ICosmeticRenderer<T>?
}