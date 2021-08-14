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

package xyz.matthewtgm.requisite.mixins.game;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.matthewtgm.requisite.Requisite;
import xyz.matthewtgm.requisite.events.BetterInputEvent;
import xyz.matthewtgm.requisite.events.RequisiteEvent;
import xyz.matthewtgm.requisite.keybinds.KeyBind;

import java.util.List;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Shadow @Final private static Logger logger;

    @Shadow public GuiScreen currentScreen;

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;checkGLError(Ljava/lang/String;)V", ordinal = 0))
    private void onForgeStarted(CallbackInfo ci) {
        /* Attempt to force-load Requisite. */
        Logger requisiteLogger = Requisite.getInstance().getLogger();
        boolean requisiteLoaded = ForgeHelper.isModLoaded(Requisite.ID, Requisite.VER);
        requisiteLogger.warn("Is requisite loaded? " + requisiteLoaded);
        if (!requisiteLoaded) {
            try {
                requisiteLogger.warn("Requisite was unable to load naturally... Attempting to force-load!");
                Requisite.getInstance().initialize(new FMLInitializationEvent());
            } catch (Exception e) {
                e.printStackTrace();
                requisiteLogger.error("Failed to force-load Requisite... Things aren't going to work properly!");
            }
        }
    }

    @Inject(method = "dispatchKeypresses", at = @At("HEAD"), cancellable = true)
    private void onKeypressesDispatched(CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new BetterInputEvent.KeyboardInputEvent(
                Keyboard.getEventKey(),
                Keyboard.getEventCharacter(),
                Keyboard.getKeyCount(),
                Keyboard.isRepeatEvent(),
                Keyboard.areRepeatEventsEnabled()
        ))) {
            ci.cancel();
        }
        if (true) {
            return;
        }
        if (Requisite.getManager() == null || Requisite.getManager().getKeyBindManager() == null || Requisite.getManager().getKeyBindManager().getKeyBinds() == null) {
            logger.info("It appears Requisite was not loaded properly, please report this!");
            return;
        }
        List<KeyBind> keyBinds = Requisite.getManager().getKeyBindManager().getKeyBinds();
        boolean wereRepeatEventsEnabled = Keyboard.areRepeatEventsEnabled();
        Keyboard.enableRepeatEvents(true);
        int key = Keyboard.getEventKey();
        boolean down = Keyboard.getEventKeyState();
        boolean repeated = Keyboard.isRepeatEvent();
        if (!keyBinds.isEmpty()) {
            Requisite requisite = Requisite.getInstance();
            for (KeyBind keyBind : keyBinds) {
                if (!keyBind.worksInGuis() && currentScreen == null || keyBind.worksInGuis()) {
                    if (key == keyBind.getKey()) {
                        if (down && !repeated) {
                            if (MinecraftForge.EVENT_BUS.post(new RequisiteEvent.KeyEvent.KeyPressedEvent.Pre(requisite, keyBind)))
                                continue;
                            keyBind.pressed();
                            MinecraftForge.EVENT_BUS.post(new RequisiteEvent.KeyEvent.KeyPressedEvent.Post(requisite, keyBind));
                        }
                        if (down && repeated) {
                            if (MinecraftForge.EVENT_BUS.post(new RequisiteEvent.KeyEvent.KeyHeldEvent.Pre(requisite, keyBind)))
                                continue;
                            keyBind.held();
                            MinecraftForge.EVENT_BUS.post(new RequisiteEvent.KeyEvent.KeyHeldEvent.Post(requisite, keyBind));
                        }
                        if (!down) {
                            if (MinecraftForge.EVENT_BUS.post(new RequisiteEvent.KeyEvent.KeyReleasedEvent.Pre(requisite, keyBind)))
                                continue;
                            keyBind.released();
                            MinecraftForge.EVENT_BUS.post(new RequisiteEvent.KeyEvent.KeyReleasedEvent.Post(requisite, keyBind));
                        }
                    }
                }
            }
        }
        Keyboard.enableRepeatEvents(wereRepeatEventsEnabled);
    }

}