package xyz.deftu.requisite.mixins.gui;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.mango.Strings;
import xyz.deftu.mango.collections.Pair;
import xyz.deftu.requisite.Requisite;

import java.util.UUID;

@Mixin({InGameHud.class})
public class InGameHudMixin {

    @Unique private Text newMessageText;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/PlayerListHud;render(Lnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/ScoreboardObjective;)V")))
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo callbackInfo) {
        Requisite.getInstance().getInternalEventManager().handleRenderTick(tickDelta);
    }

    @Inject(method = "addChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ClientChatListener;onChatMessage(Lnet/minecraft/network/MessageType;Lnet/minecraft/text/Text;Ljava/util/UUID;)V"), cancellable = true)
    private void onChatMessageAdded(MessageType type, Text message, UUID sender, CallbackInfo ci) {
        Pair<String, Boolean> chatMessageEvent = Requisite.getInstance().getInternalEventManager().handleChatMessageSent(message.asString());
        if (chatMessageEvent.right())
            ci.cancel();

        try {
            String newMessage = chatMessageEvent.first();
            if (!Strings.isNullOrEmpty(newMessage))
                newMessageText = message.getClass().getConstructor(String.class).newInstance(chatMessageEvent.first());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ModifyArg(method = "addChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ClientChatListener;onChatMessage(Lnet/minecraft/network/MessageType;Lnet/minecraft/text/Text;Ljava/util/UUID;)V"))
    private Text modifySentText(Text original) {
        return newMessageText == null ? original : newMessageText;
    }

}