package xyz.deftu.requisite.mixins.gui;

import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({GuiChat.class})
public interface GuiChatInvoker {
    @Invoker void invokeSendAutocompleteRequest(String first, String second);
}