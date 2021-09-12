package xyz.qalcyo.requisite.util;

import net.minecraft.client.MinecraftClient;
import xyz.qalcyo.requisite.core.util.IMouseHelper;

public class MouseHelper implements IMouseHelper {

    public double getMouseX() {
        return MinecraftClient.getInstance().mouse.getX();
    }

    public double getMouseY() {
        return MinecraftClient.getInstance().mouse.getY();
    }

    public boolean isMouseButtonDown() {
        return MinecraftClient.getInstance().mouse.wasLeftButtonClicked() || MinecraftClient.getInstance().mouse.wasMiddleButtonClicked() || MinecraftClient.getInstance().mouse.wasRightButtonClicked();
    }

}