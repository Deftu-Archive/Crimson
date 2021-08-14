package xyz.matthewtgm.requisite.core.rendering;

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

    CharSequence trim(CharSequence input, int width, boolean reverse);
    CharSequence trim(CharSequence input, int width);

    void drawText(String text, float x, float y, int colour, boolean shadow);
    void drawText(String text, float x, float y, int colour);
    void drawText(String text, float x, float y);
    void drawText(String text, double x, double y, int colour, boolean shadow);
    void drawText(String text, double x, double y, int colour);
    void drawText(String text, double x, double y);

    void drawCenteredText(String text, float x, float y, int colour, boolean shadow);
    void drawCenteredText(String text, float x, float y, int colour);
    void drawCenteredText(String text, float x, float y);
    void drawCenteredText(String text, double x, double y, int colour, boolean shadow);
    void drawCenteredText(String text, double x, double y, int colour);
    void drawCenteredText(String text, double x, double y);

    void drawScaledText(String text, float scale, float x, float y, int colour, boolean shadow);
    void drawScaledText(String text, float scale, float x, float y, int colour);
    void drawScaledText(String text, float scale, float x, float y);
    void drawScaledText(String text, float scale, double x, double y, int colour, boolean shadow);
    void drawScaledText(String text, float scale, double x, double y, int colour);
    void drawScaledText(String text, float scale, double x, double y);

    void drawChromaText(String text, float x, float y, boolean shadow);
    void drawChromaText(String text, float x, float y);
    void drawChromaText(String text, double x, double y, boolean shadow);
    void drawChromaText(String text, double x, double y);

    void drawCenteredScaledText(String text, float scale, float x, float y, int colour, boolean shadow);
    void drawCenteredScaledText(String text, float scale, float x, float y, int colour);
    void drawCenteredScaledText(String text, float scale, float x, float y);
    void drawCenteredScaledText(String text, float scale, double x, double y, int colour, boolean shadow);
    void drawCenteredScaledText(String text, float scale, double x, double y, int colour);
    void drawCenteredScaledText(String text, float scale, double x, double y);

    void drawCenteredChromaText(String text, float x, float y, boolean shadow);
    void drawCenteredChromaText(String text, float x, float y);
    void drawCenteredChromaText(String text, double x, double y, boolean shadow);
    void drawCenteredChromaText(String text, double x, double y);

    void drawScaledChromaText(String text, float x, float y, boolean shadow);
    void drawScaledChromaText(String text, float x, float y);
    void drawScaledChromaText(String text, double x, double y, boolean shadow);
    void drawScaledChromaText(String text, double x, double y);

    void drawCenteredScaledChromaText(String text, float x, float y, boolean shadow);
    void drawCenteredScaledChromaText(String text, float x, float y);
    void drawCenteredScaledChromaText(String text, double x, double y, boolean shadow);
    void drawCenteredScaledChromaText(String text, double x, double y);

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

}