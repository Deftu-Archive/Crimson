package xyz.matthewtgm.requisite.core.hud;

import xyz.matthewtgm.mango.collections.Pair;
import xyz.matthewtgm.mango.collections.impl.MutablePair;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.data.IScreenPosition;
import xyz.matthewtgm.requisite.core.settings.PositionSetting;
import xyz.matthewtgm.tgmconfig.settings.BaseSetting;
import xyz.matthewtgm.tgmconfig.settings.impl.BooleanSetting;

import java.util.ArrayList;
import java.util.List;

public abstract class HudElement {

    /* Metadata. */
    /**
     * The name of this element, visible inside the configuration menu.
     */
    private final String name;
    /**
     * Left is width, right is height.
     */
    protected final Pair<Integer, Integer> dimensions;

    /* Services. */
    /**
     * Instance of the main Requisite class.
     */
    protected final IRequisite requisite;

    /* Config. */
    /**
     * All settings held inside this element.
     */
    private final List<BaseSetting<?>> settings;
    protected final BooleanSetting toggleSetting;
    protected final PositionSetting positionSetting;

    public HudElement(String name, IRequisite requisite) {
        this.name = name;
        this.dimensions = new MutablePair<>();

        this.requisite = requisite;

        this.settings = new ArrayList<>();
        this.settings.add(toggleSetting = new BooleanSetting("Toggle", false));
        this.settings.add(positionSetting = new PositionSetting("Position", requisite.getManager().getPositionHelper().createDefaultPosition()));
    }

    public abstract void render(IScreenPosition position, float partialTicks);

    public final String getName() {
        return name;
    }

    public final Pair<Integer, Integer> getDimensions() {
        return dimensions;
    }

    public final List<BaseSetting<?>> getSettings() {
        return settings;
    }

    public final BooleanSetting getToggleSetting() {
        return toggleSetting;
    }

    public final PositionSetting getPositionSetting() {
        return positionSetting;
    }

}