package xyz.qalcyo.requisite.core.util;

import xyz.qalcyo.requisite.core.data.IScreenPosition;

public interface IPositionHelper {
    IScreenPosition createDefaultPosition();
    IScreenPosition createPosition(float x, float y);
}