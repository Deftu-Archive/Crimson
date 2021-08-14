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

package xyz.matthewtgm.requisite.util;

import gg.essential.universal.wrappers.UPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.util.messages.IMessageQueue;
import xyz.matthewtgm.requisite.core.util.messages.MessageQueueEntry;

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

    }

    public void queue(String message) {

    }

    public void run(MessageQueueEntry entry) {
        if (tickCounter % entry.getDelay() == 0) {
            if (entry == null || entry.getMessage() == null)
                return;

            tickCounter = 0;

            if (UPlayer.hasPlayer())
                UPlayer.getPlayer().sendChatMessage(entry.getMessage());
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