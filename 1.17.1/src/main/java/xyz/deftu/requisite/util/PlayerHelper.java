package xyz.deftu.requisite.util;

import net.minecraft.client.MinecraftClient;
import xyz.deftu.requisite.core.util.IPlayerHelper;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return MinecraftClient.getInstance().player != null;
    }

}