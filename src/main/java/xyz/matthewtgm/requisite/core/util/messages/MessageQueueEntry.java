package xyz.matthewtgm.requisite.core.util.messages;

public class MessageQueueEntry {

    private static final int DEFAULT_DELAY = 20;

    private final String message;
    private final int delay;

    public MessageQueueEntry(String message, int delay) {
        this.message = message;
        this.delay = delay;
    }

    public MessageQueueEntry(String message) {
        this(message, DEFAULT_DELAY);
    }

    public String getMessage() {
        return message;
    }

    public int getDelay() {
        return delay;
    }

}