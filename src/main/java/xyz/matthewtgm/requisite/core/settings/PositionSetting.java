package xyz.matthewtgm.requisite.core.settings;

import xyz.matthewtgm.requisite.core.data.IScreenPosition;
import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

public class PositionSetting extends BaseSetting<IScreenPosition> {

    public PositionSetting(String name, IScreenPosition val) {
        super(name, val);
    }

    public float getX() {
        return val.getX();
    }

    public PositionSetting updateX(float x) {
        val.setX(x);
        return this;
    }

    public float getY() {
        return val.getY();
    }

    public PositionSetting updateY(float y) {
        val.setY(y);
        return this;
    }

    public PositionSetting update(float x, float y) {
        val.setPosition(x, y);
        return this;
    }

}