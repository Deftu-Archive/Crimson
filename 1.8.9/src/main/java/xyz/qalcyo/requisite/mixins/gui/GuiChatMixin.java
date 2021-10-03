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

package xyz.qalcyo.requisite.mixins.gui;

import com.google.common.collect.ObjectArrays;
import net.minecraft.client.gui.GuiChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qalcyo.requisite.Requisite;

@Mixin({GuiChat.class})
public class GuiChatMixin {

    @Unique private String[] concatenatedResponse;

    @Inject(method = "sendAutocompleteRequest", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/ClientCommandHandler;autoComplete(Ljava/lang/String;Ljava/lang/String;)V", shift = At.Shift.AFTER))
    private void onAutoCompleteRequestSent(String p_146405_1_, String p_146405_2_, CallbackInfo ci) {
        Requisite.getInstance().getCommandRegistry().processAutoCompletion(p_146405_1_);
    }

    @Inject(method = "onAutocompleteResponse", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V"))
    public void onAutoCompleteResponse_complete(String[] p_146406_1_, CallbackInfo ci) {
        String[] complete = Requisite.getInstance().getCommandRegistry().getCachedAutoCompletion();
        if (complete != null) {
            concatenatedResponse = ObjectArrays.concat(complete, p_146406_1_, String.class);
        } else {
            concatenatedResponse = p_146406_1_;
        }
    }

    @ModifyVariable(method = "onAutocompleteResponse", at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V"))
    public String[] modifyResponse(String[] original) {
        return concatenatedResponse;
    }

}