package xyz.deftu.requisite.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public class GlHelper {

    public void startScissorBox(int x, int y, int width, int height) {
        totalScissor(x, y, width, height);
    }

    public void startScissorBox(float x, float y, float width, float height) {
        startScissorBox((int) x, (int) y, (int) width, (int) height);
    }

    public void endScissorBox() {
        RenderSystem.disableScissor();
    }

    public void totalScissor(double xPosition, double yPosition, double width, double height) {
        Window window = MinecraftClient.getInstance().getWindow();
        int windowWidth = window.getWidth();
        int scaledWidth = window.getScaledWidth();
        int windowHeight = window.getHeight();
        int scaledHeight = window.getScaledHeight();
        RenderSystem.enableScissor((int) ((xPosition * windowWidth) / scaledWidth), (int) (((scaledHeight - (yPosition + height)) * windowHeight) / scaledHeight), (int) (width * windowWidth / scaledWidth), (int) (height * windowHeight / scaledHeight));
    }

}