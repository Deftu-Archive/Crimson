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

package xyz.qalcyo.requisite.rendering;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.NotNull;
import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.rendering.IEnhancedFontRenderer;
import xyz.qalcyo.requisite.core.util.ColourHelper;

import java.util.List;

public class EnhancedFontRenderer implements IEnhancedFontRenderer {

    private final IRequisite requisite;
    private final TextRenderer textRenderer;

    public EnhancedFontRenderer(IRequisite requisite) {
        this.requisite = requisite;
        this.textRenderer = MinecraftClient.getInstance().textRenderer;
    }

    public int getFontHeight() {
        return textRenderer.fontHeight;
    }

    public int getWidth(CharSequence input) {
        return textRenderer.getWidth(input.toString());
    }

    public int getWidth(char input) {
        return textRenderer.getWidth(Character.toString(input));
    }

    public @NotNull CharSequence trim(CharSequence input, int width, boolean reverse) {
        return textRenderer.trimToWidth(input.toString(), width, reverse);
    }

    public @NotNull CharSequence trim(@NotNull CharSequence input, int width) {
        return trim(input, width, false);
    }

    public void drawText(MatrixStack matrices, String text, float x, float y, int colour, boolean shadow) {
        LiteralText literal = new LiteralText(text);
        if (shadow) {
            textRenderer.drawWithShadow(matrices, literal, x, y, colour);
        } else {
            textRenderer.draw(matrices, literal, x, y, colour);
        }
    }

    public void drawText(@NotNull String text, float x, float y, int colour, boolean shadow) {
        drawText(new MatrixStack(), text, x, y, colour, shadow);
    }

    public void drawText(@NotNull String text, float x, float y, int colour) {
        drawText(text, x, y, colour, false);
    }

    public void drawText(@NotNull String text, float x, float y) {
        drawText(text, x, y, -1);
    }

    public void drawText(MatrixStack matrices, String text, double x, double y, int colour, boolean shadow) {
        drawText(matrices, text, (float) x, (float) y, colour, shadow);
    }

    public void drawText(@NotNull String text, double x, double y, int colour, boolean shadow) {
        drawText(text, (float) x, (float) y, colour, shadow);
    }

    public void drawText(@NotNull String text, double x, double y, int colour) {
        drawText(text, x, y, colour, false);
    }

    public void drawText(@NotNull String text, double x, double y) {
        drawText(text, x, y, -1);
    }

    public void drawCenteredText(MatrixStack matrices, String text, float x, float y, int colour, boolean shadow) {
        drawText(matrices, text, makeCentered(text, x), y, colour, shadow);
    }

    public void drawCenteredText(@NotNull String text, float x, float y, int colour, boolean shadow) {
        drawCenteredText(new MatrixStack(), text, makeCentered(text, x), y, colour, shadow);
    }

    public void drawCenteredText(@NotNull String text, float x, float y, int colour) {
        drawCenteredText(text, x, y, colour, false);
    }

    public void drawCenteredText(@NotNull String text, float x, float y) {
        drawCenteredText(text, x, y, -1);
    }

    public void drawCenteredText(MatrixStack matrices, String text, double x, double y, int colour, boolean shadow) {
        drawCenteredText(matrices, text, (float) x, (float) y, colour, shadow);
    }

    public void drawCenteredText(@NotNull String text, double x, double y, int colour, boolean shadow) {
        drawCenteredText(new MatrixStack(), text, x, y, colour, shadow);
    }

    public void drawCenteredText(@NotNull String text, double x, double y, int colour) {
        drawCenteredText(text, x, y, colour, false);
    }

    public void drawCenteredText(@NotNull String text, double x, double y) {
        drawCenteredText(text, x, y, -1);
    }

    public void drawScaledText(@NotNull String text, float scale, float x, float y, int colour, boolean shadow) {
        MatrixStack matrices = new MatrixStack();
        matrices.push();
        matrices.scale(scale, scale, scale);
        drawText(matrices, text, x, y, colour, shadow);
        matrices.pop();
    }

    public void drawScaledText(@NotNull String text, float scale, float x, float y, int colour) {
        drawScaledText(text, scale, x, y, colour, false);
    }

    public void drawScaledText(@NotNull String text, float scale, float x, float y) {
        drawScaledText(text, scale, x, y, -1);
    }

    public void drawScaledText(@NotNull String text, float scale, double x, double y, int colour, boolean shadow) {
        drawScaledText(text, scale, (float) x, (float) y, colour, shadow);
    }

    public void drawScaledText(@NotNull String text, float scale, double x, double y, int colour) {
        drawScaledText(text, scale, x, y, colour, false);
    }

    public void drawScaledText(@NotNull String text, float scale, double x, double y) {
        drawScaledText(text, scale, x, y, -1);
    }

    public void drawChromaText(MatrixStack matrices, String text, float x, float y, boolean shadow) {
        for (char c : text.toCharArray()) {
            int colour = ColourHelper.INSTANCE.getChroma(x, y).getRGB();
            String str = String.valueOf(c);
            drawText(matrices, str, x, y, colour, shadow);
            x += getWidth(c);
        }
    }

    public void drawChromaText(@NotNull String text, float x, float y, boolean shadow) {
        drawChromaText(new MatrixStack(), text, x, y, shadow);
    }

    public void drawChromaText(@NotNull String text, float x, float y) {
        drawChromaText(text, x, y, false);
    }

    public void drawChromaText(MatrixStack matrices, String text, double x, double y, boolean shadow) {
        drawChromaText(matrices, text, (float) x, (float) y, shadow);
    }

    public void drawChromaText(@NotNull String text, double x, double y, boolean shadow) {
        drawChromaText(text, (float) x, (float) y, shadow);
    }

    public void drawChromaText(@NotNull String text, double x, double y) {
        drawChromaText(text, x, y, false);
    }

    public void drawCenteredScaledText(@NotNull String text, float scale, float x, float y, int colour, boolean shadow) {
        MatrixStack matrices = new MatrixStack();
        matrices.push();
        matrices.scale(scale, scale, scale);
        drawCenteredText(text, x, y, colour, shadow);
        matrices.pop();
    }

    public void drawCenteredScaledText(@NotNull String text, float scale, float x, float y, int colour) {
        drawCenteredScaledText(text, scale, x, y, colour, false);
    }

    public void drawCenteredScaledText(@NotNull String text, float scale, float x, float y) {
        drawCenteredScaledText(text, scale, x, y, -1);
    }

    public void drawCenteredScaledText(@NotNull String text, float scale, double x, double y, int colour, boolean shadow) {
        drawCenteredScaledText(text, scale, (float) x, (float) y, colour, shadow);
    }

    public void drawCenteredScaledText(@NotNull String text, float scale, double x, double y, int colour) {
        drawCenteredScaledText(text, scale, x, y, colour, false);
    }

    public void drawCenteredScaledText(@NotNull String text, float scale, double x, double y) {
        drawCenteredScaledText(text, scale, x, y, -1);
    }

    public void drawCenteredChromaText(@NotNull String text, float x, float y, boolean shadow) {
        drawChromaText(text, makeCentered(text, x), y, shadow);
    }

    public void drawCenteredChromaText(@NotNull String text, float x, float y) {
        drawCenteredChromaText(text, x, y, false);
    }

    public void drawCenteredChromaText(@NotNull String text, double x, double y, boolean shadow) {
        drawCenteredChromaText(text, (float) x, (float) y, shadow);
    }

    public void drawCenteredChromaText(@NotNull String text, double x, double y) {
        drawCenteredChromaText(text, x, y, false);
    }

    public void drawScaledChromaText(@NotNull String text, float scale, float x, float y, boolean shadow) {
        MatrixStack matrices = new MatrixStack();
        matrices.push();
        matrices.scale(scale, scale, scale);
        drawChromaText(matrices, text, x, y, shadow);
        matrices.pop();
    }

    public void drawScaledChromaText(@NotNull String text, float scale, float x, float y) {
        drawScaledChromaText(text, scale, x, y, false);
    }

    public void drawScaledChromaText(@NotNull String text, float scale, double x, double y, boolean shadow) {
        drawScaledChromaText(text, scale, (float) x, (float) y, shadow);
    }

    public void drawScaledChromaText(@NotNull String text, float scale, double x, double y) {
        drawScaledChromaText(text, scale, x, y, false);
    }

    public void drawCenteredScaledChromaText(@NotNull String text, float scale, float x, float y, boolean shadow) {
        MatrixStack matrices = new MatrixStack();
        matrices.push();
        matrices.scale(scale, scale, scale);
        drawChromaText(matrices, text, makeCentered(text, x), y, shadow);
        matrices.pop();
    }

    public void drawCenteredScaledChromaText(@NotNull String text, float scale, float x, float y) {
        drawCenteredScaledChromaText(text, scale, x, y, false);
    }

    public void drawCenteredScaledChromaText(@NotNull String text, float scale, double x, double y, boolean shadow) {
        drawCenteredScaledChromaText(text, scale, (float) x, (float) y, shadow);
    }

    public void drawCenteredScaledChromaText(@NotNull String text, float scale, double x, double y) {
        drawCenteredScaledChromaText(text, scale, x, y, false);
    }

    public @NotNull List<String> wrapTextLines(@NotNull String text, int width, @NotNull String split) {
        //TODO: Implement this
        return IEnhancedFontRenderer.super.wrapTextLines(text, width, split);
    }

    public @NotNull String wrapText(@NotNull String text, int width, @NotNull String split) {
        //TODO: Implement this
        return IEnhancedFontRenderer.super.wrapText(text, width, split);
    }

    public float makeCentered(@NotNull CharSequence input, float f) {
        //TODO: Implement this
        return IEnhancedFontRenderer.super.makeCentered(input, f);
    }
}