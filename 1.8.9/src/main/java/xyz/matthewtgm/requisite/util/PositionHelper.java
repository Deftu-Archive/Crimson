package xyz.matthewtgm.requisite.util;

import xyz.matthewtgm.requisite.core.util.IPositionHelper;
import xyz.matthewtgm.requisite.data.ScreenPosition;

public class PositionHelper implements IPositionHelper {

    public ScreenPosition createDefaultPosition() {
        return ScreenPosition.fromRaw(30, 30);
    }

    public ScreenPosition createPosition(float x, float y) {
        return ScreenPosition.fromRaw(x, y);
    }

}