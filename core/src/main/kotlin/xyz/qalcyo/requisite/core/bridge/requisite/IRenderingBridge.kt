package xyz.qalcyo.requisite.core.bridge.requisite

import xyz.qalcyo.requisite.core.bridge.IBridgeContainer
import xyz.qalcyo.requisite.core.util.VertexFormats

interface IRenderingBridge : IBridgeContainer {
    fun draw()

    fun begin(glMode: Int, vertexFormat: VertexFormats)
    fun end()

    fun nextVertex()

    fun pos(x: Number, y: Number, z: Number): IRenderingBridge
    fun tex(u: Number, v: Number): IRenderingBridge
    fun col(r: Int, g: Int, b: Int, a: Int = 255): IRenderingBridge
    fun col(r: Float, g: Float, b: Float, a: Float = 1.0f): IRenderingBridge
}