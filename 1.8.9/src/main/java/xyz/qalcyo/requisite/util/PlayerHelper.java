package xyz.qalcyo.requisite.util;

import net.minecraft.client.Minecraft;
import xyz.qalcyo.requisite.core.util.IPlayerHelper;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

}