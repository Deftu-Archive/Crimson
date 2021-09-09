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

package xyz.deftu.requisite.util;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.util.messages.IMessageQueue;
import xyz.deftu.requisite.core.util.messages.MessageQueueEntry;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueue implements IMessageQueue {

    private final IRequisite requisite;

    protected final Queue<MessageQueueEntry> queue = new ConcurrentLinkedQueue<>();
    private long tickCounter;

    public MessageQueue(IRequisite requisite) {
        this.requisite = requisite;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void queue(String message, int delay) {
        queue.add(new MessageQueueEntry(message, delay));
    }

    public void queue(String message) {
        queue.add(new MessageQueueEntry(message));
    }

    public void run(MessageQueueEntry entry) {
        if (tickCounter % entry.getDelay() == 0) {
            if (entry == null || entry.getMessage() == null)
                return;

            tickCounter = 0;

            if (Minecraft.getMinecraft().thePlayer != null)
                Minecraft.getMinecraft().thePlayer.sendChatMessage(entry.getMessage());
            queue.remove(entry);
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            tickCounter++;
            if (!queue.isEmpty()) {
                MessageQueueEntry current = queue.element();
                if (current != null) {
                    run(current);
                }
            }
        }
    }

}