package xyz.deftu.requisite.util;

import net.minecraft.client.Minecraft;
import xyz.deftu.requisite.core.util.IPlayerHelper;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

}