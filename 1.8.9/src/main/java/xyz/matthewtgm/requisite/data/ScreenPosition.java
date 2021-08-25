package xyz.matthewtgm.requisite.data;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import xyz.matthewtgm.requisite.Requisite;
import xyz.matthewtgm.requisite.core.data.IScreenPosition;

public class ScreenPosition implements IScreenPosition {

    private float x, y;

    public ScreenPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        return resolution.getScaledWidth() * x;
    }

    public ScreenPosition setX(float x) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        this.x = Requisite.getInstance().getManager().getMathHelper().percentageOf(x, 0, resolution.getScaledWidth());
        return this;
    }

    public float getY() {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        return resolution.getScaledHeight() * y;
    }

    public ScreenPosition setY(float y) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        this.y = Requisite.getInstance().getManager().getMathHelper().percentageOf(0, y, resolution.getScaledHeight());
        return this;
    }

    public IScreenPosition setPosition(float x, float y) {
        setX(x);
        setY(y);
        return this;
    }

    public String toString() {
        return toJson().getAsString();
    }

    public static ScreenPosition fromRaw(float x, float y) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        return new ScreenPosition(Requisite.getInstance().getManager().getMathHelper().percentageOf(x, 0, resolution.getScaledWidth()), Requisite.getInstance().getManager().getMathHelper().percentageOf(y, 0, resolution.getScaledHeight()));
    }

    public static ScreenPosition fromScaled(float x, float y) {
        return new ScreenPosition(x, y);
    }

}