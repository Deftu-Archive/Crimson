package xyz.qalcyo.requisite.core.cosmetics

interface ICosmeticHelper<T> {
    fun initialize(cosmeticManager: CosmeticManager<T>?)
    val uuid: String?

    fun hasCosmetic(player: T, cosmetic: ICosmetic<T>?): Boolean
}