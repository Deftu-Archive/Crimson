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

package xyz.qalcyo.requisite;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import xyz.qalcyo.requisite.core.IEventListener;
import xyz.qalcyo.requisite.core.IRequisite;
import gg.essential.universal.UMatrixStack;
import xyz.qalcyo.requisite.popups.PopupManager;

public class RequisiteEventListener implements IEventListener {

    private final IRequisite requisite;

    public RequisiteEventListener(IRequisite requisite) {
        this.requisite = requisite;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        if (event.type == 0 || event.type == 1) {
            event.setCanceled(requisite.getInternalEventManager().handleChatMessageReceived(event.message.getUnformattedText(), event.type));
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        requisite.getInternalEventManager().handleKeyInput(Keyboard.getEventKey(), Keyboard.getEventKeyState(), Keyboard.isRepeatEvent());
    }

    @SubscribeEvent
    public void onGameOverlayRendered(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            requisite.getInternalEventManager().handleHudRender(event.partialTicks);
            PopupManager.INSTANCE.getListPopups().forEach((pair) -> pair.component2().drawPopup(UMatrixStack.Compat.INSTANCE.get()));
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        requisite.getInternalEventManager().handleTick();
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        requisite.getInternalEventManager().handleRenderTick(event.renderTickTime);
        if (Requisite.getInstance().keyBinding.isPressed()) {
            PopupManager.INSTANCE.getListPopups().remove(PopupManager.INSTANCE.getListPopups().size() - 1);
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        requisite.getInternalEventManager().handleWorldLoad();
    }

}