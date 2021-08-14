package xyz.matthewtgm.requisite.util;

import gg.essential.universal.wrappers.UPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import xyz.matthewtgm.requisite.core.util.ChatColour;
import xyz.matthewtgm.requisite.core.util.IChatHelper;

public class ChatHelper implements IChatHelper {

    public void send(String prefix, String message) {
        if (prefix == null || message == null)
            return;
        if (!UPlayer.hasPlayer())
            return;
        ChatComponentText converted = (ChatComponentText) new ChatComponentText(prefix).appendText(ChatColour.RESET + " ").appendText(message);
        if (post(converted))
            return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(converted);
    }

    public void send(String message) {
        if (message == null)
            return;
        if (!UPlayer.hasPlayer())
            return;
        ChatComponentText converted = new ChatComponentText(message);
        if (post(converted))
            return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(converted);
    }

    private boolean post(IChatComponent component) {
        return MinecraftForge.EVENT_BUS.post(new ClientChatReceivedEvent((byte) 1, component));
    }

}