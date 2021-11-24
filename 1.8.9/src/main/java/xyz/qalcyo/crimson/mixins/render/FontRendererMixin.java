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

package xyz.qalcyo.crimson.mixins.render;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.qalcyo.crimson.Crimson;
import xyz.qalcyo.crimson.core.events.FontRendererEvent;
import xyz.qalcyo.eventbus.QalcyoEventBus;

@Mixin({FontRenderer.class})
public class FontRendererMixin {

    @Unique
    private FontRendererEvent.RenderStringEvent drawStringEvent;
    @Unique
    private FontRendererEvent.WidthGottenEvent widthGottenEvent;

    @Inject(method = "renderString", at = @At("HEAD"))
    private void onStringRendered(String text, float x, float y, int colour, boolean dropShadow, CallbackInfoReturnable<Integer> cir) {
        Crimson crimson = Crimson.getInstance();
        if (crimson != null) {
            QalcyoEventBus crimsonEventBus = crimson.getEventBus();
            if (crimsonEventBus != null) {
                crimsonEventBus.post(drawStringEvent = new FontRendererEvent.RenderStringEvent(text, x, y, colour, dropShadow));
            }
        }
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private String onStringRendered_modifyText(String original) {
        return drawStringEvent.string;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float onStringRendered_modifyX(float original) {
        return drawStringEvent.x;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    private float onStringRendered_modifyY(float original) {
        return drawStringEvent.y;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private int onStringRendered_modifyColor(int original) {
        return drawStringEvent.colour;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private boolean onStringRendered_modifyDropShadow(boolean original) {
        return drawStringEvent.dropShadow;
    }

    @Inject(method = "getStringWidth", at = @At("HEAD"))
    private void onStringWidthGotten(String text, CallbackInfoReturnable<Integer> cir) {
        Crimson crimson = Crimson.getInstance();
        if (crimson != null) {
            QalcyoEventBus crimsonEventBus = crimson.getEventBus();
            if (crimsonEventBus != null) {
                crimsonEventBus.post(widthGottenEvent = new FontRendererEvent.WidthGottenEvent(text));
            }
        }
    }

    @ModifyVariable(method = "getStringWidth", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private String onStringWidthGotten_modifyText(String original) {
        return widthGottenEvent.string;
    }

}