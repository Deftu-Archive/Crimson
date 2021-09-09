package xyz.deftu.requisite.util;

import xyz.deftu.requisite.core.util.IPositionHelper;
import xyz.deftu.requisite.data.ScreenPosition;

public class PositionHelper implements IPositionHelper {

    public ScreenPosition createDefaultPosition() {
        return ScreenPosition.fromRaw(30, 30);
    }

    public ScreenPosition createPosition(float x, float y) {
        return ScreenPosition.fromRaw(x, y);
    }

}