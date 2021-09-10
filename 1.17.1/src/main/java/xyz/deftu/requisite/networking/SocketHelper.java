package xyz.deftu.requisite.networking;

import net.minecraft.client.MinecraftClient;
import xyz.deftu.requisite.Requisite;
import xyz.deftu.requisite.core.networking.ISocketHelper;
import xyz.deftu.requisite.util.ChatHelper;

import java.util.UUID;

public class SocketHelper implements ISocketHelper {

    public UUID getUuid() {
        return MinecraftClient.getInstance().getSession().getProfile().getId();
    }

    public String getName() {
        return MinecraftClient.getInstance().getSession().getUsername();
    }

    public void chat(String message) {
        ChatHelper chatHelper = Requisite.getInstance().getChatHelper();
        if (chatHelper != null) {
            chatHelper.send(message);
        }
    }

}