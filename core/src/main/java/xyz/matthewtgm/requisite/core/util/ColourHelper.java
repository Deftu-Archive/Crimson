package xyz.matthewtgm.requisite.core.util;

import java.awt.*;

public class ColourHelper {

    /**
     * @return A changing colour based on the users' computer time. Simulates a "chroma" colour.
     * @author MatthewTGM
     */
    public static int timeBasedChroma() {
        long l = System.currentTimeMillis();
        return Color.HSBtoRGB(l % 2000L / 2000.0f, 0.8f, 0.8f);
    }

    public static Color getChroma(double x, double y) {
        float v = 2000.0f;
        return new Color(Color.HSBtoRGB((float)((System.currentTimeMillis() - x * 10.0 * 1.0 - y * 10.0 * 1.0) % v) / v, 0.8f, 0.8f));
    }

    public static int getAlpha(int colour) {
        return (colour >> 24 & 255);
    }

}