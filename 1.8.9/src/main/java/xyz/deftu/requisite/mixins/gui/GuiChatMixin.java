package xyz.deftu.requisite.mixins.gui;

import com.google.common.collect.ObjectArrays;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.requisite.Requisite;
import xyz.deftu.requisite.commands.CommandCompleter;

import java.util.Arrays;

@Mixin({GuiChat.class})
public abstract class GuiChatMixin {

    @Unique private String[] concatenatedResponse;
    @Unique private CommandCompleter commandCompleter;

    /* Updated default command completion. */

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

    /* Custom command completion. */

    @Inject(method = "initGui", at = @At("HEAD"))
    public void onGuiInit(CallbackInfo ci) {
        commandCompleter = new CommandCompleter((GuiChat) (Object) this);
    }

    @Inject(method = "keyTyped", at = @At("HEAD"), cancellable = true)
    public void onKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        if (commandCompleterInitialized()) {
            commandCompleter.keyTyped(typedChar, keyCode, ci);
        }
    }

    @Inject(method = "drawScreen", at = @At("TAIL"))
    public void onScreenDrawn(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if (commandCompleterInitialized()) {
            commandCompleter.draw(mouseX, mouseY, partialTicks);
        }
    }

    @Inject(method = "onGuiClosed", at = @At("TAIL"))
    public void onGuiClosed(CallbackInfo ci) {
        if (commandCompleterInitialized()) {
            commandCompleter.close();
        }
    }

    @Inject(method = "onAutocompleteResponse", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/lang/String;substring(I)Ljava/lang/String;", shift = At.Shift.BEFORE))
    public void onAutocompleteResponse_commandCompleter(String[] p_146406_1_, CallbackInfo ci) {
        if (commandCompleter != null) {
            System.out.println(Arrays.asList(p_146406_1_));
            commandCompleter.autocompleted(p_146406_1_);
        }
    }

    @Unique
    private boolean commandCompleterInitialized() {
        return commandCompleter != null && commandCompleter.initialized();
    }

}