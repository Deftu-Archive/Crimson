/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.mixins.gui;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qalcyo.crimson.transformers.hooks.impl.GuiMainMenuHook;

@Mixin({GuiMainMenu.class})
public class GuiMainMenuMixin {

    @Unique private final GuiMainMenuHook crimsonHook = new GuiMainMenuHook((GuiMainMenu)(Object)this);

    @Inject(method = "initGui", at = @At("HEAD"))
    private void onGuiInit(CallbackInfo ci) {
        crimsonHook.initialize();
    }

    @Inject(method = "drawScreen", at = @At("TAIL"))
    private void onScreenDrawn(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        crimsonHook.draw(mouseX, mouseY, partialTicks);
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void onMouseClicked(int mouseX, int mouseY, int mouseButton, CallbackInfo ci) {
        crimsonHook.mouseClicked(mouseX, mouseY, mouseButton);
    }

}