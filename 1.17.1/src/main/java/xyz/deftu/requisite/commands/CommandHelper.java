package xyz.deftu.requisite.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import xyz.deftu.requisite.core.commands.ICommandHelper;

public class CommandHelper implements ICommandHelper {

    public boolean isInChat() {
        return MinecraftClient.getInstance().currentScreen instanceof ChatScreen;
    }

}