package xyz.deftu.requisite.util;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import xyz.deftu.requisite.core.util.IRenderHelper;

public class RenderHelper extends DrawableHelper implements IRenderHelper {

    public void drawRect(MatrixStack matrices, int left, int top, int right, int bottom, int colour) {
        fill(matrices, left, top, right, bottom, colour);
    }

    public void drawRect(int left, int top, int right, int bottom, int colour) {
        drawRect(new MatrixStack(), left, top, right, bottom, colour);
    }

    public void drawRectEnhanced(MatrixStack matrices, int x, int y, int width, int height, int colour) {
        drawRect(matrices, x, y, x + width, y + height, colour);
    }

    public void drawRectEnhanced(int x, int y, int width, int height, int colour) {
        drawRectEnhanced(new MatrixStack(), x, y, width, height, colour);
    }

    public void drawHollowRect(int x, int y, int width, int height, int thickness, int colour) {
        drawHorizontalLine(x, x + width, y, thickness, colour);
        drawHorizontalLine(x, x + width, y + height, thickness, colour);
        drawVerticalLine(x, y + height, y, thickness, colour);
        drawVerticalLine(x + width, y + height, y, thickness, colour);
    }

    public void drawHorizontalLine(MatrixStack matrices, int start, int end, int y, int thickness, int colour) {
        for (int i = 0; i < thickness; i++) {
            drawHorizontalLine(matrices, start, end, y + i, colour);
        }
    }

    public void drawHorizontalLine(int start, int end, int y, int thickness, int colour) {
        drawHorizontalLine(new MatrixStack(), start, end, y, thickness, colour);
    }

    public void drawVerticalLine(MatrixStack matrices, int x, int start, int end, int thickness, int colour) {
        for (int i = 0; i < thickness; i++) {
            drawVerticalLine(matrices, x + i, start, end, colour);
        }
    }

    public void drawVerticalLine(int x, int start, int end, int thickness, int colour) {
        drawVerticalLine(new MatrixStack(), x, start, end, thickness, colour);
    }

}