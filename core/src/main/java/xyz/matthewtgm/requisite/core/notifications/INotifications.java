package xyz.matthewtgm.requisite.core.notifications;

import java.util.function.Consumer;

public interface INotifications {
    void push(String title, String description, NotificationColour colour, int duration, Consumer<Notification> clickListener);
    void push(String title, String description, Consumer<Notification> clickListener);
    void push(String title, String description, int duration);
    void push(String title, String description, NotificationColour colour);
    void push(String title, String description, NotificationColour colour, int duration);
    void push(String title, String description, int duration, Consumer<Notification> clickListener);
    void push(String title, String description, NotificationColour colour, Consumer<Notification> clickListener);
    void push(String title, String description);

    void render();
}