package xyz.deftu.requisite.core.hud;

import xyz.deftu.mango.collections.Pair;
import xyz.deftu.mango.collections.impl.MutablePair;
import xyz.deftu.requisite.core.data.IScreenPosition;
import xyz.deftu.requisite.core.settings.PositionSetting;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.simpleconfig.settings.BaseSetting;
import xyz.deftu.simpleconfig.settings.impl.BooleanSetting;

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
    protected IRequisite requisite;

    /* Config. */
    /**
     * All settings held inside this element.
     */
    private final List<BaseSetting<?>> settings;
    protected BooleanSetting toggleSetting;
    protected PositionSetting positionSetting;

    public HudElement(String name) {
        this.name = name;
        this.dimensions = new MutablePair<>();

        this.settings = new ArrayList<>();
    }

    public final void initialize(IRequisite requisite) {
        this.requisite = requisite;

        this.settings.add(toggleSetting = new BooleanSetting("Toggle", false));
        this.settings.add(positionSetting = new PositionSetting("Position", requisite.getPositionHelper().createDefaultPosition()));
    }

    public abstract void render(IScreenPosition position, float partialTicks);

    public final boolean isMouseInside(int mouseX, int mouseY) {
        IScreenPosition position = this.positionSetting.get();
        return (mouseX >= position.getX()
                        && mouseX <= position.getX() +
                        dimensions.left())
                        && (mouseY >= position.getY()
                        && mouseY <= position.getY() +
                        dimensions.right());
    }

    public final String getName() {
        return name;
    }

    public final String getJsonKey() {
        return name.toLowerCase().replace(' ', '_');
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