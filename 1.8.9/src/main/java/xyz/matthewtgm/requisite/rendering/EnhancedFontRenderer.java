package xyz.matthewtgm.requisite.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import xyz.matthewtgm.requisite.core.rendering.IEnhancedFontRenderer;

public class EnhancedFontRenderer implements IEnhancedFontRenderer {

    public FontRenderer getFontRenderer() {
        return Minecraft.getMinecraft().fontRendererObj;
    }

    public int getWidth(CharSequence input) {
        return getFontRenderer().getStringWidth(input.toString());
    }

    public int getWidth(char input) {
        return getFontRenderer().getCharWidth(input);
    }

    public CharSequence trim(CharSequence input, int width, boolean reverse) {
        return getFontRenderer().trimStringToWidth(input.toString(), width, reverse);
    }

    public CharSequence trim(CharSequence input, int width) {
        return getFontRenderer().trimStringToWidth(input.toString(), width);
    }

    public void drawText(String text, float x, float y, int colour, boolean shadow) {
        getFontRenderer().drawString(text, x, y, colour, shadow);
    }

    public void drawText(String text, float x, float y, int colour) {
        drawText(text, x, y, colour, false);
    }

    public void drawText(String text, float x, float y) {
        drawText(text, x, y, -1);
    }

    public void drawText(String text, double x, double y, int colour, boolean shadow) {
        drawText(text, (float) x, (float) y, colour, shadow);
    }

    public void drawText(String text, double x, double y, int colour) {
        drawText(text, x, y, colour, false);
    }

    public void drawText(String text, double x, double y) {
        drawText(text, x, y, -1);
    }

    public void drawCenteredText(String text, float x, float y, int colour, boolean shadow) {
        drawText(text, x - getWidth(text), y, colour, shadow);
    }

    public void drawCenteredText(String text, float x, float y, int colour) {

    }

    public void drawCenteredText(String text, float x, float y) {

    }

    public void drawCenteredText(String text, double x, double y, int colour, boolean shadow) {

    }

    public void drawCenteredText(String text, double x, double y, int colour) {

    }

    public void drawCenteredText(String text, double x, double y) {

    }

    public void drawScaledText(String text, float scale, float x, float y, int colour, boolean shadow) {

    }

    public void drawScaledText(String text, float scale, float x, float y, int colour) {

    }

    public void drawScaledText(String text, float scale, float x, float y) {

    }

    public void drawScaledText(String text, float scale, double x, double y, int colour, boolean shadow) {

    }

    public void drawScaledText(String text, float scale, double x, double y, int colour) {

    }

    public void drawScaledText(String text, float scale, double x, double y) {

    }

    public void drawChromaText(String text, float x, float y, boolean shadow) {

    }

    public void drawChromaText(String text, float x, float y) {

    }

    public void drawChromaText(String text, double x, double y, boolean shadow) {

    }

    public void drawChromaText(String text, double x, double y) {

    }

    public void drawCenteredScaledText(String text, float scale, float x, float y, int colour, boolean shadow) {

    }

    public void drawCenteredScaledText(String text, float scale, float x, float y, int colour) {

    }

    public void drawCenteredScaledText(String text, float scale, float x, float y) {

    }

    public void drawCenteredScaledText(String text, float scale, double x, double y, int colour, boolean shadow) {

    }

    public void drawCenteredScaledText(String text, float scale, double x, double y, int colour) {

    }

    public void drawCenteredScaledText(String text, float scale, double x, double y) {

    }

    public void drawCenteredChromaText(String text, float x, float y, boolean shadow) {

    }

    public void drawCenteredChromaText(String text, float x, float y) {

    }

    public void drawCenteredChromaText(String text, double x, double y, boolean shadow) {

    }

    public void drawCenteredChromaText(String text, double x, double y) {

    }

    public void drawScaledChromaText(String text, float x, float y, boolean shadow) {

    }

    public void drawScaledChromaText(String text, float x, float y) {

    }

    public void drawScaledChromaText(String text, double x, double y, boolean shadow) {

    }

    public void drawScaledChromaText(String text, double x, double y) {

    }

    public void drawCenteredScaledChromaText(String text, float x, float y, boolean shadow) {

    }

    public void drawCenteredScaledChromaText(String text, float x, float y) {

    }

    public void drawCenteredScaledChromaText(String text, double x, double y, boolean shadow) {

    }

    public void drawCenteredScaledChromaText(String text, double x, double y) {

    }

}