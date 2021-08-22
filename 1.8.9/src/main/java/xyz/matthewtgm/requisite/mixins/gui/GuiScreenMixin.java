package xyz.matthewtgm.requisite.mixins.gui;

import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.matthewtgm.mango.collections.Pair;
import xyz.matthewtgm.requisite.Requisite;

@Mixin({GuiScreen.class})
public class GuiScreenMixin {

    @Unique private String newSentMessage;

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;sendChatMessage(Ljava/lang/String;)V"), cancellable = true)
    private void onChatMessageSent(String msg, boolean addToChat, CallbackInfo ci) {
        Pair<String, Boolean> chatMessageEvent = Requisite.getInstance().getManager().getInternalEventManager().handleChatMessageSent(msg);
        if (chatMessageEvent.right())
            ci.cancel();
        newSentMessage = chatMessageEvent.left();
    }

    @ModifyArg(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;sendChatMessage(Ljava/lang/String;)V", ordinal = 0))
    private String modifyMessageSent(String original) {
        return newSentMessage;
    }

}