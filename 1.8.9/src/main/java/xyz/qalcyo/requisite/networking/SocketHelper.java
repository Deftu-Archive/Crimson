package xyz.qalcyo.requisite.networking;

import net.minecraft.client.Minecraft;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.util.ChatHelper;
import xyz.qalcyo.requisite.core.networking.ISocketHelper;

import java.util.UUID;

public class SocketHelper implements ISocketHelper {

    public UUID getUuid() {
        return Minecraft.getMinecraft().getSession().getProfile().getId();
    }

    public String getName() {
        return Minecraft.getMinecraft().getSession().getUsername();
    }

    public void chat(String message) {
        ChatHelper chatHelper = Requisite.getInstance().getChatHelper();
        if (chatHelper != null) {
            chatHelper.send(message);
        }
    }

}