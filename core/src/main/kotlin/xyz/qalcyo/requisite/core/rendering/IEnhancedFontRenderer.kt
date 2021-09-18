/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.core.rendering

import java.util.*

interface IEnhancedFontRenderer {

    /**
     * @param input The input sequence to get the width of.
     * @return The width of the input.
     */
    fun getWidth(input: CharSequence): Int

    /**
     * @param input The input character to get the width of.
     * @return The width of the input.
     */
    fun getWidth(input: Char): Int
    fun trim(input: CharSequence, width: Int, reverse: Boolean): CharSequence
    fun trim(input: CharSequence, width: Int): CharSequence
    fun drawText(text: String, x: Float, y: Float, colour: Int, shadow: Boolean)
    fun drawText(text: String, x: Float, y: Float, colour: Int)
    fun drawText(text: String, x: Float, y: Float)
    fun drawText(text: String, x: Double, y: Double, colour: Int, shadow: Boolean)
    fun drawText(text: String, x: Double, y: Double, colour: Int)
    fun drawText(text: String, x: Double, y: Double)
    fun drawCenteredText(text: String, x: Float, y: Float, colour: Int, shadow: Boolean)
    fun drawCenteredText(text: String, x: Float, y: Float, colour: Int)
    fun drawCenteredText(text: String, x: Float, y: Float)
    fun drawCenteredText(text: String, x: Double, y: Double, colour: Int, shadow: Boolean)
    fun drawCenteredText(text: String, x: Double, y: Double, colour: Int)
    fun drawCenteredText(text: String, x: Double, y: Double)
    fun drawScaledText(text: String, scale: Float, x: Float, y: Float, colour: Int, shadow: Boolean)
    fun drawScaledText(text: String, scale: Float, x: Float, y: Float, colour: Int)
    fun drawScaledText(text: String, scale: Float, x: Float, y: Float)
    fun drawScaledText(text: String, scale: Float, x: Double, y: Double, colour: Int, shadow: Boolean)
    fun drawScaledText(text: String, scale: Float, x: Double, y: Double, colour: Int)
    fun drawScaledText(text: String, scale: Float, x: Double, y: Double)
    fun drawChromaText(text: String, x: Float, y: Float, shadow: Boolean)
    fun drawChromaText(text: String, x: Float, y: Float)
    fun drawChromaText(text: String, x: Double, y: Double, shadow: Boolean)
    fun drawChromaText(text: String, x: Double, y: Double)
    fun drawCenteredScaledText(text: String, scale: Float, x: Float, y: Float, colour: Int, shadow: Boolean)
    fun drawCenteredScaledText(text: String, scale: Float, x: Float, y: Float, colour: Int)
    fun drawCenteredScaledText(text: String, scale: Float, x: Float, y: Float)
    fun drawCenteredScaledText(text: String, scale: Float, x: Double, y: Double, colour: Int, shadow: Boolean)
    fun drawCenteredScaledText(text: String, scale: Float, x: Double, y: Double, colour: Int)
    fun drawCenteredScaledText(text: String, scale: Float, x: Double, y: Double)
    fun drawCenteredChromaText(text: String, x: Float, y: Float, shadow: Boolean)
    fun drawCenteredChromaText(text: String, x: Float, y: Float)
    fun drawCenteredChromaText(text: String, x: Double, y: Double, shadow: Boolean)
    fun drawCenteredChromaText(text: String, x: Double, y: Double)
    fun drawScaledChromaText(text: String, scale: Float, x: Float, y: Float, shadow: Boolean)
    fun drawScaledChromaText(text: String, scale: Float, x: Float, y: Float)
    fun drawScaledChromaText(text: String, scale: Float, x: Double, y: Double, shadow: Boolean)
    fun drawScaledChromaText(text: String, scale: Float, x: Double, y: Double)
    fun drawCenteredScaledChromaText(text: String, scale: Float, x: Float, y: Float, shadow: Boolean)
    fun drawCenteredScaledChromaText(text: String, scale: Float, x: Float, y: Float)
    fun drawCenteredScaledChromaText(text: String, scale: Float, x: Double, y: Double, shadow: Boolean)
    fun drawCenteredScaledChromaText(text: String, scale: Float, x: Double, y: Double)

    /**
     * Adapted from XanderLib under GPL 3.0 license
     * https://github.com/isXander/XanderLib/blob/main/LICENSE
     *
     * @author isXander
     */
    fun wrapTextLines(text: String, width: Int, split: String): List<String> {
        val wrapped = wrapText(text, width, split)
        return if (wrapped.isEmpty()) ArrayList() else Arrays.asList(*wrapped.split("\n").toTypedArray())
    }

    /**
     * Adapted from XanderLib under GPL 3.0 license
     * https://github.com/isXander/XanderLib/blob/main/LICENSE
     *
     * @author isXander
     */
    fun wrapText(text: String, width: Int, split: String): String {
        val words = text.split("($split|\n)").toTypedArray()
        var lineLength = 0
        val output = StringBuilder()
        for (i in words.indices) {
            var word = words[i]
            if (i != words.size - 1) word += split
            val wordLength = getWidth(word)
            if (lineLength + wordLength <= width) {
                output.append(word)
                lineLength += wordLength
            } else if (wordLength <= width) {
                output.append("\n").append(word)
                lineLength = wordLength
            } else output.append(wrapText(word, width, "")).append(split)
        }
        return output.toString()
    }

    fun makeCentered(input: CharSequence, f: Float) =
        f - getWidth(input) / 2

}