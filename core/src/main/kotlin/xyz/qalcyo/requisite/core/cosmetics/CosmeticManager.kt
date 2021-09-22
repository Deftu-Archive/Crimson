package xyz.qalcyo.requisite.core.cosmetics

import xyz.qalcyo.mango.Lists
import xyz.qalcyo.mango.Maps
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.networking.packets.cosmetics.CosmeticRetrievePacket
import java.util.*

class CosmeticManager<T>(
    requisite: IRequisite,
    cosmeticInitializer: ICosmeticInitializer<T>,
    cosmeticHelper: ICosmeticHelper<T>
) {
    val playerData: Map<String, PlayerCosmeticData>
    private val cosmeticData: MutableList<CosmeticData>
    private val cosmetics: List<ICosmetic<T>>
    val cosmeticInitializer: ICosmeticInitializer<T>
    private val cosmeticHelper: ICosmeticHelper<T>
    fun initialize() {
        cosmeticHelper.initialize(this)

        /* Public cosmetics. */cosmeticData.add(
            CosmeticData.from(
                "Beehive Cloak",
                CosmeticType.CLOAK,
                texture("cloak/beehive_cloak.png")
            )
        )
        cosmeticData.add(CosmeticData.from("Booster Cloak", CosmeticType.CLOAK, texture("cloak/booster_cloak.png")))
        cosmeticData.add(
            CosmeticData.from(
                "Bug Hunter Cloak",
                CosmeticType.CLOAK,
                texture("cloak/bug_hunter_cloak.png")
            )
        )
        cosmeticData.add(CosmeticData.from("Developer Cloak", CosmeticType.CLOAK, texture("cloak/developer_cloak.png")))
        cosmeticData.add(
            CosmeticData.from(
                "Dragon's Eye Cloak",
                CosmeticType.CLOAK,
                texture("cloak/dragon_eye_cloak.png")
            )
        )
        cosmeticData.add(
            CosmeticData.from(
                "Enchanter Cloak",
                CosmeticType.CLOAK,
                texture("cloak/enchanter_cloak.gif"),
                CosmeticFlag.ANIMATED
            )
        )
        cosmeticData.add(CosmeticData.from("Keyswitch Cloak", CosmeticType.CLOAK, texture("cloak/keyswitch_cloak.png")))
        cosmeticData.add(CosmeticData.from("Modder Cloak", CosmeticType.CLOAK, texture("cloak/modder_cloak.png")))
        cosmeticData.add(CosmeticData.from("Partner Cloak", CosmeticType.CLOAK, texture("cloak/partner_cloak.png")))
        cosmeticData.add(
            CosmeticData.from(
                "Vaporwave Cloak",
                CosmeticType.CLOAK,
                texture("cloak/vaporwave_cloak.gif"),
                CosmeticFlag.ANIMATED
            )
        )
        //cosmeticData.add(CosmeticData.from("Icy Cave Cloak", CosmeticType.CLOAK, texture("cloak/icy_cave_cloak.png")));
        cosmeticData.add(
            CosmeticData.from(
                "Space Rats Cloak",
                CosmeticType.CLOAK,
                texture("cloak/space_rats_cloak.png")
            )
        )

        /* Exclusive cosmetics. */cosmeticData.add(
            CosmeticData.from(
                "Strebbypatty Cloak",
                CosmeticType.CLOAK,
                texture("cloak/exclusive/streb_cloak.png")
            )
        )
        for (cosmeticData in cosmeticData) {
            cosmeticInitializer.initialize(cosmetics, cosmeticData)
        }
        cosmeticInitializer.finish(this, cosmetics)
    }

    fun hasCosmetic(player: T, cosmetic: ICosmetic<T>?): Boolean {
        return cosmeticHelper.hasCosmetic(player, cosmetic)
    }

    fun fromId(id: String?): CosmeticData? {
        var value: CosmeticData? = null
        for (data in cosmeticData) {
            if (data.isId(id!!)) {
                value = data
                break
            }
        }
        return value
    }

    private fun texture(path: String): CosmeticTexture {
        return CosmeticTexture(path, CosmeticManager::class.java.getResourceAsStream("/cosmetics/$path"))
    }

    fun getCosmeticData(): List<CosmeticData> {
        return Collections.unmodifiableList(cosmeticData)
    }

    fun getCosmetics(): List<ICosmetic<T>> {
        return Collections.unmodifiableList(cosmetics)
    }

    init {
        playerData = Maps.newHashMap()
        cosmeticData = Lists.newLinkedList()
        cosmetics = Lists.newLinkedList()
        this.cosmeticInitializer = cosmeticInitializer
        this.cosmeticHelper = cosmeticHelper
        requisite.requisiteSocket.send(CosmeticRetrievePacket(cosmeticHelper.uuid))
    }
}