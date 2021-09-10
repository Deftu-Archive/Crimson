package xyz.deftu.requisite.util;

import net.minecraft.client.MinecraftClient;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.events.TickEvent;
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
        requisite.getEventBus().register(TickEvent.class, this::onClientTick);
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

            if (MinecraftClient.getInstance().player != null)
                MinecraftClient.getInstance().player.sendChatMessage(entry.getMessage());
            queue.remove(entry);
        }
    }

    public void onClientTick(TickEvent event) {
        tickCounter++;
        if (!queue.isEmpty()) {
            MessageQueueEntry current = queue.element();
            if (current != null) {
                run(current);
            }
        }
    }

}