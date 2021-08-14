package xyz.matthewtgm.requisite.core.util.messages;

public interface IMessageQueue {
    void queue(String message, int delay);
    void queue(String message);

    void run(MessageQueueEntry entry);
}