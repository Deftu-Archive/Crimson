package xyz.deftu.requisite.mixins.gui;

import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.mango.collections.Pair;
import xyz.deftu.requisite.Requisite;

@Mixin({GuiScreen.class})
public class GuiScreenMixin {

    @Unique private String newSentMessage;

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;sendChatMessage(Ljava/lang/String;)V"), cancellable = true)
    private void onChatMessageSent(String msg, boolean addToChat, CallbackInfo ci) {
        System.out.println(msg);
        Pair<String, Boolean> chatMessageEvent = Requisite.getInstance().getInternalEventManager().handleChatMessageSent(msg);
        if (chatMessageEvent.right()) {
            ci.cancel();
        }

        newSentMessage = chatMessageEvent.left();
    }

    @ModifyArg(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;sendChatMessage(Ljava/lang/String;)V", ordinal = 0))
    private String modifyMessageSent(String original) {
        return newSentMessage;
    }

}