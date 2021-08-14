package xyz.matthewtgm.requisite.core.util;

public interface IChatHelper {
    /**
     * Sends a message to the player's chat with a prefix included.
     *
     * @param prefix The prefix of the message.
     * @param message The message to send.
     */
    void send(String prefix, String message);

    /**
     * Sends a message to the player's chat.
     *
     * @param message The message to send.
     */
    void send(String message);
}