package xyz.qalcyo.requisite.core.cosmetics

interface ICosmetic<T> {
    fun render(player: T, partialTicks: Float)
    fun render(cosmeticManager: CosmeticManager<T>, player: T, partialTicks: Float) {
        if (cosmeticManager.hasCosmetic(player, this)) {
            render(player, partialTicks)
        }
    }

    fun data(): CosmeticData
}