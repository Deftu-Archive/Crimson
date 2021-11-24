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

package xyz.qalcyo.crimson.dsl

import xyz.qalcyo.crimson.core.CrimsonAPI
import xyz.qalcyo.crimson.core.util.VertexFormats
import java.awt.Color

private val renderer = CrimsonAPI.retrieveInstance().bridge.renderingBridge

fun tessellate(glMode: Int, vertexFormat: VertexFormats, worldRendererScope: WorldRendererScope.() -> Unit) {
    WorldRendererScope(glMode, vertexFormat).apply(worldRendererScope)
    renderer.end()
    renderer.draw()
}

class WorldRendererScope(
    glMode: Int,
    vertexFormat: VertexFormats,
) {
    init {
        renderer.begin(glMode, vertexFormat)
    }

    fun vertex(vertexScope: VertexScope.() -> Unit) {
        with(VertexScope().apply(vertexScope)) {
            if (pos != null) renderer.pos(pos!!.first, pos!!.second, pos!!.third)
            if (tex != null) renderer.tex(tex!!.first, tex!!.second)
            if (color != null) renderer.col(color!!.red, color!!.green, color!!.blue, color!!.alpha)
        }
        renderer.nextVertex()
    }
}

class VertexScope {
    var pos: Triple<Double, Double, Double>? = null
        private set
    var tex: Pair<Double, Double>? = null
        private set
    var color: Color? = null
        private set

    fun pos(x: Number, y: Number, z: Number) {
        pos = Triple(x.toDouble(), y.toDouble(), z.toDouble())
    }
    fun tex(u: Number, v: Number) {
        tex = u.toDouble() to v.toDouble()
    }

    fun color(red: Float, green: Float, blue: Float, alpha: Float = 1f) {
        color = Color(red * 255, green * 255, blue * 255, alpha * 255)
    }
    fun color(red: Int, green: Int, blue: Int, alpha: Int = 255) {
        color = Color(red, green, blue, alpha)
    }
    fun color(rgba: Int) {
        color = Color(rgba)
    }
    fun color(color: Color) {
        this.color = color
    }
}