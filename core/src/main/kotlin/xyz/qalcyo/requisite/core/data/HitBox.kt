package xyz.qalcyo.requisite.core.data

import xyz.qalcyo.json.entities.JsonObject

class HitBox(var x: Float, var y: Float, var width: Float, var height: Float) {

    constructor(json: JsonObject) : this(
        json["x"].asFloat,
        json["y"].asFloat,
        json["width"].asFloat,
        json["height"].asFloat
    )

    fun clone(): HitBox {
        return HitBox(x, y, width, height)
    }

    fun isInBounds(x: Float, y: Float): Boolean {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height
    }
}