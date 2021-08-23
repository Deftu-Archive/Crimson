package xyz.matthewtgm.requisite.util;

import net.minecraft.client.Minecraft;
import xyz.matthewtgm.requisite.core.util.IPlayerHelper;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

}