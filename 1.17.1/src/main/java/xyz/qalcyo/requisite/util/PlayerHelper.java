package xyz.qalcyo.requisite.util;

import net.minecraft.client.MinecraftClient;
import xyz.qalcyo.requisite.core.util.IPlayerHelper;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return MinecraftClient.getInstance().player != null;
    }

}