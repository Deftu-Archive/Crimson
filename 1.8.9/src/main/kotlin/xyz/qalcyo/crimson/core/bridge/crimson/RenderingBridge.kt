/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core.bridge.crimson

import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import xyz.qalcyo.crimson.core.util.VertexFormats

class RenderingBridge : IRenderingBridge {

    val tessellator by lazy { Tessellator.getInstance() }
    val renderer by lazy { tessellator.worldRenderer }

    override fun initialize() {
    }

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