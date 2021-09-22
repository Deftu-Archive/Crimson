package xyz.qalcyo.requisite.core.cosmetics

import xyz.qalcyo.json.entities.JsonObject
import java.util.*

class CosmeticData internal constructor(
    val name: String,
    val id: String?,
    val type: CosmeticType,
    val texture: CosmeticTexture,
    vararg flags: CosmeticFlag
) {
    private val flags: MutableList<CosmeticFlag>
    fun isId(id: String): Boolean {
        return this.id != null && this.id == id
    }

    fun isType(type: CosmeticType): Boolean {
        return this.type == type
    }

    fun getFlags(): List<CosmeticFlag> {
        return flags
    }

    fun hasFlag(flag: CosmeticFlag): Boolean {
        return flags.contains(flag)
    }

    override fun toString(): String {
        return JsonObject()
            .add("name", name)
            .add("id", id)
            .add("type", type)
            .add("texture", texture)
            .add("flags", flags)
            .asString
    }

    companion object {
        fun from(
            name: String,
            id: String?,
            type: CosmeticType,
            texture: CosmeticTexture,
            vararg flags: CosmeticFlag
        ): CosmeticData {
            return CosmeticData(name, id, type, texture, *flags)
        }

        fun from(
            name: String,
            type: CosmeticType,
            texture: CosmeticTexture,
            vararg flags: CosmeticFlag
        ): CosmeticData {
            return from(name, name.uppercase(Locale.ENGLISH).replace(' ', '_'), type, texture, *flags)
        }
    }

    init {
        this.flags = LinkedList()
        flags.forEach {
            this.flags.add(it)
        }
    }
}
