package xyz.matthewtgm.requisite.core.util;

public interface IRenderHelper {
    void drawRoundedRect(int x, int y, int width, int height, int radius, int colour);
    void drawHollowRoundedRect(int x, int y, int width, int height, double thickness, int colour);

    void drawArc(int x, int y, int radius, int start, int end, int colour);
    void drawHollowArc(int x, int y, int radius, int start, int end, double thickness, int colour);

    void drawRect(int left, int top, int right, int bottom, int colour);
    void drawRectEnhanced(int x, int y, int width, int height, int colour);

    void drawHollowRect(int x, int y, int width, int height, int thickness, int colour);

    void drawHorizontalLine(int start, int end, int y, int thickness, int colour);
    void drawVerticalLine(int x, int start, int end, int thickness, int colour);
}