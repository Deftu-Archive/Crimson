package xyz.deftu.requisite.core.util;

import xyz.deftu.requisite.core.data.IScreenPosition;

public interface IPositionHelper {
    IScreenPosition createDefaultPosition();
    IScreenPosition createPosition(float x, float y);
}