package xyz.qalcyo.requisite.core.bridge.requisite

import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import xyz.qalcyo.requisite.core.util.VertexFormats

class RenderingBridge : IRenderingBridge {
    val tessellator by lazy { Tessellator.getInstance() }
    val renderer by lazy { tessellator.worldRenderer }

    override fun initialize() {}

    override fun draw() = tessellator.draw()

    override fun begin(glMode: Int, vertexFormat: VertexFormats) {
        val format = when (vertexFormat) {
            VertexFormats.POSITION -> DefaultVertexFormats.POSITION
            VertexFormats.POSITION_TEXTURE -> DefaultVertexFormats.POSITION_TEX
            VertexFormats.POSITION_COLOR -> DefaultVertexFormats.POSITION_COLOR
            VertexFormats.POSITION_COLOR_TEXTURE -> DefaultVertexFormats.POSITION_TEX_COLOR
        }

        renderer.begin(glMode, format)
    }

    override fun end() = renderer.endVertex()
    override fun nextVertex() = renderer.endVertex()

    override fun pos(x: Number, y: Number, z: Number): IRenderingBridge {
        renderer.pos(x.toDouble(), y.toDouble(), z.toDouble())
        return this
    }

    override fun tex(u: Number, v: Number): IRenderingBridge {
        renderer.tex(u.toDouble(), v.toDouble())
        return this
    }

    override fun col(r: Int, g: Int, b: Int, a: Int): IRenderingBridge {
        renderer.color(r, g, b, a)
        return this
    }

    override fun col(r: Float, g: Float, b: Float, a: Float): IRenderingBridge {
        renderer.color(r, g, b, a)
        return this
    }
}