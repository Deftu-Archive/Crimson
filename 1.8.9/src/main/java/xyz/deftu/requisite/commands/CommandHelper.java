package xyz.deftu.requisite.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import xyz.deftu.requisite.core.commands.ICommandHelper;

public class CommandHelper implements ICommandHelper {

    public boolean isInChat() {
        return Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().currentScreen instanceof GuiChat;
    }

}