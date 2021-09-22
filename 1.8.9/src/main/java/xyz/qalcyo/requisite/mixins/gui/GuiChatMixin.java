package xyz.qalcyo.requisite.mixins.gui;

import com.google.common.collect.ObjectArrays;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qalcyo.requisite.Requisite;

@Mixin({GuiChat.class})
public abstract class GuiChatMixin {

    @Unique private String[] concatenatedResponse;

    @Inject(method = "sendAutocompleteRequest", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ClientCommandHandler;autoComplete(Ljava/lang/String;Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void onAutoCompleteRequestSent(String p_146405_1_, String p_146405_2_, CallbackInfo ci) {
        Requisite.getInstance().getCommandRegistry().processAutoCompletion(p_146405_1_);
    }

    @Inject(method = "onAutocompleteResponse", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V"))
    public void onAutoCompleteResponse_complete(String[] p_146406_1_, CallbackInfo ci) {
        String[] complete = Requisite.getInstance().getCommandRegistry().getCachedAutoCompletion();
        if (complete != null) {
            concatenatedResponse = ObjectArrays.concat(complete, p_146406_1_, String.class);
        }
    }

    @ModifyVariable(method = "onAutocompleteResponse", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V"))
    public String[] modifyResponse(String[] original) {
        return concatenatedResponse;
    }

}