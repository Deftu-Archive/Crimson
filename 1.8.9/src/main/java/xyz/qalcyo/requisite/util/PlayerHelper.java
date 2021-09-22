package xyz.qalcyo.requisite.util;

import net.minecraft.client.Minecraft;
import xyz.qalcyo.requisite.core.util.IPlayerHelper;

import java.util.UUID;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

    public UUID getUuid() {
        return Minecraft.getMinecraft().getSession().getProfile().getId();
    }

}