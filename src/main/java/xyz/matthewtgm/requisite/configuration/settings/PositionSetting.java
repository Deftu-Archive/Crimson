package xyz.matthewtgm.requisite.configuration.settings;

import xyz.matthewtgm.requisite.data.ScreenPosition;
import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

public class PositionSetting extends BaseSetting<ScreenPosition> {

    public PositionSetting(String name, ScreenPosition val) {
        super(name, val);
    }

}