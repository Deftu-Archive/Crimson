/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qalcyo.requisite.Requisite;

import java.io.File;

@Mixin({MinecraftClient.class})
public abstract class MinecraftClientMixin {

    @Shadow @Final public File runDirectory;
    @Shadow public abstract float getTickDelta();

    @Shadow @Nullable public Screen currentScreen;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;onResolutionChanged()V", shift = At.Shift.AFTER))
    private void onSplashOverlayInitialized(CallbackInfo ci) {
        if (Requisite.getInstance().finish(runDirectory)) {
            Requisite.getInstance().getLogger().info("Initialized Requisite.");
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        Requisite.getInstance().getInternalEventManager().handleTick();
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;render(FJZ)V", shift = At.Shift.AFTER))
    private void onRenderTick(boolean tick, CallbackInfo ci) {
        if (currentScreen != null) {
            Requisite.getInstance().getInternalEventManager().handleRenderTick(getTickDelta());
        }
    }

}