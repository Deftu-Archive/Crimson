package xyz.qalcyo.requisite.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import xyz.qalcyo.requisite.core.data.IScreenPosition;
import xyz.qalcyo.requisite.core.util.IPositionHelper;
import xyz.qalcyo.requisite.data.ScreenPosition;

public class PositionHelper implements IPositionHelper {

    public ScreenPosition createDefaultPosition() {
        return ScreenPosition.fromRaw(30, 30);
    }

    public ScreenPosition createPosition(float x, float y) {
        return ScreenPosition.fromRaw(x, y);
    }

    public IScreenPosition createCenteredPosition() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        return ScreenPosition.fromScaled(scaledResolution.getScaledWidth() / 2, scaledResolution.getScaledHeight() / 2);
    }

}