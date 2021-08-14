/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.matthewtgm.requisite.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.rendering.IEnhancedFontRenderer;

public class EnhancedFontRenderer implements IEnhancedFontRenderer {

    private final IRequisite requisite;

    public EnhancedFontRenderer(IRequisite requisite) {
        this.requisite = requisite;
    }

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
        drawText(text, makeCentered(text, x), y, colour, shadow);
    }

    public void drawCenteredText(String text, float x, float y, int colour) {
        drawCenteredText(text, x, y, colour, false);
    }

    public void drawCenteredText(String text, float x, float y) {
        drawCenteredText(text, x, y, -1);
    }

    public void drawCenteredText(String text, double x, double y, int colour, boolean shadow) {
        drawCenteredText(text, (float) x, (float) y, colour, shadow);
    }

    public void drawCenteredText(String text, double x, double y, int colour) {
        drawCenteredText(text, x, y, colour, false);
    }

    public void drawCenteredText(String text, double x, double y) {
        drawCenteredText(text, x, y, -1);
    }

    public void drawScaledText(String text, float scale, float x, float y, int colour, boolean shadow) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        drawText(text, x, y, colour, shadow);
        GlStateManager.popMatrix();
    }

    public void drawScaledText(String text, float scale, float x, float y, int colour) {
        drawScaledText(text, scale, x, y, colour, false);
    }

    public void drawScaledText(String text, float scale, float x, float y) {
        drawScaledText(text, scale, x, y, -1);
    }

    public void drawScaledText(String text, float scale, double x, double y, int colour, boolean shadow) {
        drawScaledText(text, scale, (float) x, (float) y, colour, shadow);
    }

    public void drawScaledText(String text, float scale, double x, double y, int colour) {
        drawScaledText(text, scale, x, y, colour, false);
    }

    public void drawScaledText(String text, float scale, double x, double y) {
        drawScaledText(text, scale, x, y, -1);
    }

    public void drawChromaText(String text, float x, float y, boolean shadow) {
        for (char c : text.toCharArray()) {
            int colour = requisite.getManager().getColourHelper().getChroma(x, y).getRGB();
            String str = String.valueOf(c);
            drawText(str, x, y, colour, shadow);
            x += getWidth(c);
        }
    }

    public void drawChromaText(String text, float x, float y) {
        drawChromaText(text, x, y, false);
    }

    public void drawChromaText(String text, double x, double y, boolean shadow) {
        drawChromaText(text, (float) x, (float) y, shadow);
    }

    public void drawChromaText(String text, double x, double y) {
        drawChromaText(text, x, y, false);
    }

    public void drawCenteredScaledText(String text, float scale, float x, float y, int colour, boolean shadow) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        drawCenteredText(text, x, y, colour, shadow);
        GlStateManager.popMatrix();
    }

    public void drawCenteredScaledText(String text, float scale, float x, float y, int colour) {
        drawCenteredScaledText(text, scale, x, y, colour, false);
    }

    public void drawCenteredScaledText(String text, float scale, float x, float y) {
        drawCenteredScaledText(text, scale, x, y, -1);
    }

    public void drawCenteredScaledText(String text, float scale, double x, double y, int colour, boolean shadow) {
        drawCenteredScaledText(text, scale, (float) x, (float) y, colour, shadow);
    }

    public void drawCenteredScaledText(String text, float scale, double x, double y, int colour) {
        drawCenteredScaledText(text, scale, x, y, colour, false);
    }

    public void drawCenteredScaledText(String text, float scale, double x, double y) {
        drawCenteredScaledText(text, scale, x, y, -1);
    }

    public void drawCenteredChromaText(String text, float x, float y, boolean shadow) {
        drawChromaText(text, makeCentered(text, x), y, shadow);
    }

    public void drawCenteredChromaText(String text, float x, float y) {
        drawCenteredChromaText(text, x, y, false);
    }

    public void drawCenteredChromaText(String text, double x, double y, boolean shadow) {
        drawCenteredChromaText(text, (float) x, (float) y, shadow);
    }

    public void drawCenteredChromaText(String text, double x, double y) {
        drawCenteredChromaText(text, x, y, false);
    }

    public void drawScaledChromaText(String text, float scale, float x, float y, boolean shadow) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        drawChromaText(text, x, y, shadow);
        GlStateManager.popMatrix();
    }

    public void drawScaledChromaText(String text, float scale, float x, float y) {
        drawScaledChromaText(text, scale, x, y, false);
    }

    public void drawScaledChromaText(String text, float scale, double x, double y, boolean shadow) {
        drawScaledChromaText(text, scale, (float) x, (float) y, shadow);
    }

    public void drawScaledChromaText(String text, float scale, double x, double y) {
        drawScaledChromaText(text, scale, x, y, false);
    }

    public void drawCenteredScaledChromaText(String text, float scale, float x, float y, boolean shadow) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        drawCenteredChromaText(text, x, y, shadow);
        GlStateManager.popMatrix();
    }

    public void drawCenteredScaledChromaText(String text, float scale, float x, float y) {
        drawCenteredScaledChromaText(text, scale, x, y, false);
    }

    public void drawCenteredScaledChromaText(String text, float scale, double x, double y, boolean shadow) {
        drawCenteredScaledChromaText(text, scale, (float) x, (float) y, shadow);
    }

    public void drawCenteredScaledChromaText(String text, float scale, double x, double y) {
        drawCenteredScaledChromaText(text, scale, x, y, false);
    }

}