package xyz.qalcyo.requisite.core.data

import xyz.qalcyo.json.entities.JsonObject
import xyz.qalcyo.json.parser.JsonParser
import java.awt.Color

class ColourRGB @JvmOverloads constructor(var r: Int, var g: Int, var b: Int, var a: Int = 255) {

    constructor(`object`: JsonObject) : this(Color(fromJson(`object`).rGBA)) {}
    constructor(input: String?) : this(JsonParser.parse(input).asJsonObject) {}
    constructor(color: Color) : this(color.red, color.green, color.blue, color.alpha) {}
    constructor(rgba: Int) : this(Color(rgba)) {}

    fun clone(): ColourRGB {
        return ColourRGB(r, g, b, a)
    }

    fun setR_builder(r: Int): ColourRGB {
        this.r = r
        return this
    }

    fun setG_builder(g: Int): ColourRGB {
        this.g = g
        return this
    }

    fun setB_builder(b: Int): ColourRGB {
        this.b = b
        return this
    }

    fun setA_builder(a: Int): ColourRGB {
        this.a = a
        return this
    }

    val rGB: Int
        get() = toJavaColor().rgb
    val rGBA: Int
        get() = toJavaColor().rgb

    fun toJavaColor(): Color {
        return Color(r, g, b, a)
    }

    fun toJson(): JsonObject {
        return JsonObject().add("red", r).add("green", g).add("blue", b).add("alpha", a)
    }

    override fun toString(): String {
        return toJson().asString
    }

    companion object {
        fun fromJson(`object`: JsonObject): ColourRGB {
            var r = 255
            var g = 255
            var b = 255
            var a = 255
            r = `object`.getOrDefault("r", r).asInt
            r = `object`.getOrDefault("red", r).asInt
            g = `object`.getOrDefault("g", g).asInt
            g = `object`.getOrDefault("green", g).asInt
            b = `object`.getOrDefault("b", b).asInt
            b = `object`.getOrDefault("blue", b).asInt
            a = `object`.getOrDefault("a", a).asInt
            a = `object`.getOrDefault("alpha", a).asInt
            return ColourRGB(r, g, b, a)
        }
    }
}