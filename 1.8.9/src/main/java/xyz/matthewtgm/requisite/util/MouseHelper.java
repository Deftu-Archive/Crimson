package xyz.matthewtgm.requisite.util;

import gg.essential.universal.UResolution;
import org.lwjgl.input.Mouse;
import xyz.matthewtgm.requisite.core.util.IMouseHelper;

public class MouseHelper implements IMouseHelper {

    public int getMouseX() {
        return Mouse.getX() * UResolution.getScaledWidth() / UResolution.getWindowWidth();
    }

    public int getMouseY() {
        return UResolution.getScaledHeight() - Mouse.getY() * UResolution.getScaledHeight() / UResolution.getWindowHeight() - 1;
    }

    public boolean isMouseButtonDown() {
        return Mouse.getEventButtonState();
    }

}