package xyz.matthewtgm.requisite.core.util;

import xyz.matthewtgm.requisite.core.data.IScreenPosition;

public interface IPositionHelper {
    IScreenPosition createDefaultPosition();
    IScreenPosition createPosition(float x, float y);
}