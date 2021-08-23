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

package xyz.matthewtgm.requisite;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import xyz.matthewtgm.requisite.core.IEventListener;
import xyz.matthewtgm.requisite.core.IRequisite;

public class RequisiteEventListener implements IEventListener {

    private final IRequisite requisite;

    public RequisiteEventListener(IRequisite requisite) {
        this.requisite = requisite;
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        if (event.type == 0 || event.type == 1) {
            event.setCanceled(requisite.getManager().getInternalEventManager().handleChatMessageReceived(event.message.getUnformattedText(), event.type));
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        requisite.getManager().getInternalEventManager().handleKeyInput(Keyboard.getEventKey());
    }

    @SubscribeEvent
    public void onGameOverlayRendered(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            requisite.getManager().getInternalEventManager().handleHudRender(event.partialTicks);
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        requisite.getManager().getInternalEventManager().handleTick();
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        requisite.getManager().getInternalEventManager().handleRenderTick(event.renderTickTime);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        requisite.getManager().getInternalEventManager().handleWorldLoad();
    }

}