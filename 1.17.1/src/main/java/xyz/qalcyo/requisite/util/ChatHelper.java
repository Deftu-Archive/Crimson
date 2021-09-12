package xyz.qalcyo.requisite.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import xyz.qalcyo.requisite.core.util.ChatColour;
import xyz.qalcyo.requisite.core.util.IChatHelper;

public class ChatHelper implements IChatHelper {

    public void send(String prefix, String message) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(prefix + ChatColour.RESET + " " + message));
    }

    public void send(String message) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new LiteralText(message));
    }

}