package xyz.matthewtgm.requisite.util;

import gg.essential.universal.UMinecraft;
import gg.essential.universal.UScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import xyz.matthewtgm.requisite.core.util.IServerHelper;

public class ServerHelper implements IServerHelper {

    public void join(String ip, int port) {
        UScreen.displayScreen(new GuiConnecting(UScreen.getCurrentScreen(), UMinecraft.getMinecraft(), ip, port));
    }

    public void join(String ip) {
        join(ip, 25565);
    }

}