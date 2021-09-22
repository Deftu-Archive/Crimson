package xyz.qalcyo.requisite.core.cosmetics

import xyz.qalcyo.json.entities.JsonObject
import java.io.InputStream

class CosmeticTexture(val name: String, val texture: InputStream) {
    override fun toString(): String {
        return JsonObject()
            .add("name", name)
            .add("texture", texture)
            .asString
    }
}