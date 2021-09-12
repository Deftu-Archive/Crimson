package xyz.qalcyo.requisite.core.settings;

import xyz.qalcyo.requisite.core.data.IScreenPosition;
import xyz.qalcyo.simpleconfig.settings.BaseSetting;

public class PositionSetting extends BaseSetting<IScreenPosition> {

    public PositionSetting(String name, IScreenPosition val) {
        super(name, val);
    }

    public float getX() {
        return value.getX();
    }

    public PositionSetting updateX(float x) {
        value.setX(x);
        return this;
    }

    public float getY() {
        return value.getY();
    }

    public PositionSetting updateY(float y) {
        value.setY(y);
        return this;
    }

    public PositionSetting update(float x, float y) {
        value.setPosition(x, y);
        return this;
    }

}