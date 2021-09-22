package xyz.qalcyo.requisite.core.data

import xyz.qalcyo.json.entities.JsonObject

interface IScreenPosition {
    val x: Float

    fun setX(x: Float): IScreenPosition?
    val y: Float

    fun setY(y: Float): IScreenPosition?
    fun setPosition(x: Float, y: Float): IScreenPosition?
    fun toJson(): JsonObject {
        val value = JsonObject()
        value.add("x", x)
        value.add("y", y)
        return value
    }
}