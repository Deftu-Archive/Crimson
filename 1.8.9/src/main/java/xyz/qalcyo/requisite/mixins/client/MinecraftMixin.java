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

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.core.events.initialization.InitializationEvent;
import xyz.qalcyo.requisite.core.events.initialization.PostInitializationEvent;
import xyz.qalcyo.requisite.core.events.initialization.PreInitializationEvent;

import java.io.File;
import java.lang.reflect.Method;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Shadow @Final public File mcDataDir;
    @Shadow @Final private Session session;

    @Unique private GameProfile gameProfile;

    @Inject(method = "startGame", at = @At("HEAD"))
    private void onGameStarted_pre(CallbackInfo ci) {
        Requisite.getInstance().getEventBus().register(InitializationEvent.class, Requisite.getInstance()::finish);
        gameProfile = session.getProfile();
        Requisite.getInstance().getEventBus().post(new PreInitializationEvent(gameProfile, mcDataDir));
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/EffectRenderer;<init>(Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void onGameStarted(CallbackInfo ci) {
        Requisite.getInstance().getEventBus().post(new InitializationEvent(gameProfile, mcDataDir));
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStarted_post(CallbackInfo ci) {
        Requisite.getInstance().getEventBus().post(new PostInitializationEvent(gameProfile, mcDataDir));
    }

    @Inject(method = "dispatchKeypresses", at = @At("HEAD"), cancellable = true)
    private void onKeypressesDispatched(CallbackInfo ci) {
        if (Requisite.getInstance().getInternalEventManager().handleKeyInput(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() : Keyboard.getEventKey(), Keyboard.getEventKeyState(), Keyboard.isRepeatEvent())) {
            ci.cancel();
        }
    }

}