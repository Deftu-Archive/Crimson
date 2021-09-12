/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qalcyo.requisite.Requisite;

import java.io.File;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Shadow @Final public File mcDataDir;

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStarted(CallbackInfo ci) {
        if (Requisite.getInstance().finish(mcDataDir)) {
            Requisite.getInstance().getLogger().info("Initialized Requisite.");
        }
    }

    @Inject(method = "dispatchKeypresses", at = @At("HEAD"), cancellable = true)
    private void onKeypressesDispatched(CallbackInfo ci) {
        if (Requisite.getInstance().getInternalEventManager().handleKeyInput(Keyboard.getEventKey(), Keyboard.getEventKeyState(), Keyboard.isRepeatEvent())) {
            ci.cancel();
        }
    }

}