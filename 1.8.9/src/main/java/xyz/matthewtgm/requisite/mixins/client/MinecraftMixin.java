package xyz.matthewtgm.requisite.mixins.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.matthewtgm.requisite.Requisite;

import java.io.File;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Shadow @Final public File mcDataDir;

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStarted(CallbackInfo ci) {
        Requisite.getInstance().initialize(mcDataDir);
    }

}