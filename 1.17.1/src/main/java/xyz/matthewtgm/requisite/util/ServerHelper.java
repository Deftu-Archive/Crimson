package xyz.matthewtgm.requisite.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import xyz.matthewtgm.requisite.core.util.IServerHelper;

public class ServerHelper implements IServerHelper {

    public void join(String ip, int port) {
        MinecraftClient.getInstance().setCurrentServerEntry(new ServerInfo("", ip + (port == -1 ? ":" + 25565 : ":" + port), false));
    }

    public void join(String ip) {
        join(ip, -1);
    }

    public String getBrand() {
        return MinecraftClient.getInstance().player.getServerBrand();
    }

    public boolean isSingleplayer() {
        return MinecraftClient.getInstance().isInSingleplayer();
    }

}