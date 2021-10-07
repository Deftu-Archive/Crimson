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

package xyz.qalcyo.requisite.core.rendering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface IEnhancedFontRenderer {

    /**
     * @param input The input sequence to get the width of.
     * @return The width of the input.
     */
    int getWidth(CharSequence input);

    /**
     * @param input The input character to get the width of.
     * @return The width of the input.
     */
    int getWidth(char input);

    /**
     * @return A trimmed version of the input param based on the width given.
     */
    CharSequence trim(CharSequence input, int width, boolean reverse);

    /**
     * @return A trimmed version of the input param based on the width given.
     */
    CharSequence trim(CharSequence input, int width);

    /**
     * Draws a String based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The X position of where to draw the text.
     * @param y      The Y position of where to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawText(String text, float x, float y, int colour, boolean shadow);

    /**
     * Draws a String based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The X position of where to draw the text.
     * @param y      The Y position of where to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawText(String text, float x, float y, int colour);

    /**
     * Draws a String based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The X position of where to draw the text.
     * @param y    The Y position of where to draw the text.
     */
    void drawText(String text, float x, float y);

    /**
     * Draws a String based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The X position of where to draw the text.
     * @param y      The Y position of where to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawText(String text, double x, double y, int colour, boolean shadow);

    /**
     * Draws a String based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The X position of where to draw the text.
     * @param y      The Y position of where to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawText(String text, double x, double y, int colour);

    /**
     * Draws a String based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The X position of where to draw the text.
     * @param y    The Y position of where to draw the text.
     */
    void drawText(String text, double x, double y);

    /**
     * Draws centered text based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredText(String text, float x, float y, int colour, boolean shadow);

    /**
     * Draws centered text based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawCenteredText(String text, float x, float y, int colour);

    /**
     * Draws centered text based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawCenteredText(String text, float x, float y);

    /**
     * Draws centered text based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredText(String text, double x, double y, int colour, boolean shadow);

    /**
     * Draws centered text based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawCenteredText(String text, double x, double y, int colour);

    /**
     * Draws centered text based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawCenteredText(String text, double x, double y);

    /**
     * Draws scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawScaledText(String text, float scale, float x, float y, int colour, boolean shadow);

    /**
     * Draws scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawScaledText(String text, float scale, float x, float y, int colour);

    /**
     * Draws scaled text based on the parameters given.
     *
     * @param text  The string to render.
     * @param scale The amount to scale the text.
     * @param x     The middle X position to draw the text.
     * @param y     The middle Y position to draw the text.
     */
    void drawScaledText(String text, float scale, float x, float y);

    /**
     * Draws scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawScaledText(String text, float scale, double x, double y, int colour, boolean shadow);

    /**
     * Draws scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawScaledText(String text, float scale, double x, double y, int colour);

    /**
     * Draws scaled text based on the parameters given.
     *
     * @param text  The string to render.
     * @param scale The amount to scale the text.
     * @param x     The middle X position to draw the text.
     * @param y     The middle Y position to draw the text.
     */
    void drawScaledText(String text, float scale, double x, double y);

    /**
     * Draws text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawChromaText(String text, float x, float y, boolean shadow);

    /**
     * Draws text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawChromaText(String text, float x, float y);

    /**
     * Draws text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawChromaText(String text, double x, double y, boolean shadow);

    /**
     * Draws text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawChromaText(String text, double x, double y);

    /**
     * Draws centered and scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredScaledText(String text, float scale, float x, float y, int colour, boolean shadow);

    /**
     * Draws centered and scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the text.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawCenteredScaledText(String text, float scale, float x, float y, int colour);

    /**
     * Draws centered and scaled text based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawCenteredScaledText(String text, float scale, float x, float y);

    /**
     * Draws centered and scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredScaledText(String text, float scale, double x, double y, int colour, boolean shadow);

    /**
     * Draws centered and scaled text based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the textt.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param colour The colour of the text drawn.
     */
    void drawCenteredScaledText(String text, float scale, double x, double y, int colour);

    /**
     * Draws centered and scaled text based on the parameters given.
     *
     * @param text  The string to render.
     * @param scale The amount to scale the textt.
     * @param x     The middle X position to draw the text.
     * @param y     The middle Y position to draw the text.
     */
    void drawCenteredScaledText(String text, float scale, double x, double y);

    /**
     * Draws centered text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredChromaText(String text, float x, float y, boolean shadow);

    /**
     * Draws centered text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawCenteredChromaText(String text, float x, float y);

    /**
     * Draws centered text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredChromaText(String text, double x, double y, boolean shadow);

    /**
     * Draws centered text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawCenteredChromaText(String text, double x, double y);

    /**
     * Draws centered text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawScaledChromaText(String text, float scale, float x, float y, boolean shadow);

    /**
     * Draws centered text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text The string to render.
     * @param x    The middle X position to draw the text.
     * @param y    The middle Y position to draw the text.
     */
    void drawScaledChromaText(String text, float scale, float x, float y);

    /**
     * Draws scaled text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawScaledChromaText(String text, float scale, double x, double y, boolean shadow);

    /**
     * Draws scaled text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text  The string to render.
     * @param scale The amount to scale the text.
     * @param x     The middle X position to draw the text.
     * @param y     The middle Y position to draw the text.
     */
    void drawScaledChromaText(String text, float scale, double x, double y);

    /**
     * Draws centered and scaled text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the text.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredScaledChromaText(String text, float scale, float x, float y, boolean shadow);

    /**
     * Draws centered and scaled text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text  The string to render.
     * @param scale The amount to scale the text.
     * @param x     The middle X position to draw the text.
     * @param y     The middle Y position to draw the text.
     */
    void drawCenteredScaledChromaText(String text, float scale, float x, float y);

    /**
     * Draws centered and scaled text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text   The string to render.
     * @param scale  The amount to scale the text.
     * @param x      The middle X position to draw the text.
     * @param y      The middle Y position to draw the text.
     * @param shadow Whether to display a text shadow.
     */
    void drawCenteredScaledChromaText(String text, float scale, double x, double y, boolean shadow);

    /**
     * Draws centered and scaled text that changes colour to represent a "chroma" effect based on the parameters given.
     *
     * @param text  The string to render.
     * @param scale The amount to scale the text.
     * @param x     The middle X position to draw the text.
     * @param y     The middle Y position to draw the text.
     */
    void drawCenteredScaledChromaText(String text, float scale, double x, double y);

    /**
     * Adapted from XanderLib under GPL 3.0 license
     * https://github.com/isXander/XanderLib/blob/main/LICENSE
     *
     * @author isXander
     */
    default List<String> wrapTextLines(String text, int width, String split) {
        String wrapped = wrapText(text, width, split);
        if (wrapped.isEmpty())
            return new ArrayList<>();
        return Arrays.asList(wrapped.split("\n"));
    }

    /**
     * Adapted from XanderLib under GPL 3.0 license
     * https://github.com/isXander/XanderLib/blob/main/LICENSE
     *
     * @author isXander
     */
    default String wrapText(String text, int width, String split) {
        String[] words = text.split("(" + split + "|\n)");
        int lineLength = 0;
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i != words.length - 1)
                word += split;
            int wordLength = getWidth(word);
            if (lineLength + wordLength <= width) {
                output.append(word);
                lineLength += wordLength;
            } else if (wordLength <= width) {
                output.append("\n").append(word);
                lineLength = wordLength;
            } else
                output.append(wrapText(word, width, "")).append(split);
        }
        return output.toString();
    }

    /**
     * @return Return a centered version of the float based on input.
     */
    default float makeCentered(CharSequence input, float f) {
        return f - getWidth(input) / 2;
    }

}