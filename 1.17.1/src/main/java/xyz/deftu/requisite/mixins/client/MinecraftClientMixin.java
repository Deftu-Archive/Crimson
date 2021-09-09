package xyz.deftu.requisite.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.requisite.Requisite;

import java.io.File;

@Mixin({MinecraftClient.class})
public abstract class MinecraftClientMixin {

    @Shadow @Final public File runDirectory;
    @Shadow public abstract float getTickDelta();

    @Shadow @Nullable public Screen currentScreen;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/SplashOverlay;init(Lnet/minecraft/client/MinecraftClient;)V"))
    private void onSplashOverlayInitialized(RunArgs args, CallbackInfo ci) {
        Requisite.getInstance().initialize(runDirectory);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        Requisite.getInstance().getManager().getInternalEventManager().handleTick();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;render(FJZ)V", shift = At.Shift.AFTER))
    private void onRenderTick(boolean tick, CallbackInfo ci) {
        if (currentScreen != null) {
            Requisite.getInstance().getManager().getInternalEventManager().handleRenderTick(getTickDelta());
        }
    }

}