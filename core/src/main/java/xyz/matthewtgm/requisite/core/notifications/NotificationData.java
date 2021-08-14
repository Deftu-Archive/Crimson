package xyz.matthewtgm.requisite.core.notifications;

public class NotificationData {
    public float time;
    public float x;
    public boolean closing;
    public boolean clicked;

    NotificationData(float time, boolean closing, boolean clicked) {
        this.time = time;
        this.closing = closing;
        this.clicked = clicked;
    }
}