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

package xyz.qalcyo.requisite.core.notifications;

import java.util.function.Consumer;

public class Notification {

    public String title;
    public String description;
    public NotificationColour colour;
    public final int duration;
    public final Consumer<Notification> clickListener;
    public final NotificationData data;

    public Notification(String title, String description, NotificationColour colour, int duration, Consumer<Notification> clickListener) {
        this.title = title;
        this.description = description;
        this.colour = colour;
        this.duration = duration;
        this.clickListener = clickListener;

        this.data = new NotificationData(0, false, false);
    }

    public Notification(String title, String description, NotificationColour colour, Consumer<Notification> clickListener) {
        this(title, description, colour, -1, clickListener);
    }

    public Notification(String title, String description, int duration) {
        this(title, description, null, duration, null);
    }

    public Notification(String title, String description, Consumer<Notification> clickListener) {
        this(title, description, null, clickListener);
    }

    public Notification(String title, String description, int duration, Consumer<Notification> clickListener) {
        this(title, description, null, duration, clickListener);
    }

    public Notification(String title, String description, NotificationColour colour) {
        this(title, description, colour, null);
    }

    public Notification(String title, String description, NotificationColour colour, int duration) {
        this(title, description, colour, duration, null);
    }

    public Notification(String title, String description) {
        this(title, description, null, null);
    }

    public Notification clone() {
        return new Notification(title, description, colour, duration, clickListener);
    }

    public void close() {
        data.closing = true;
    }

    public void click() {
        if (clickListener != null)
            clickListener.accept(this);
    }

    public int getDuration() {
        return duration;
    }

    public Consumer<Notification> getClickListener() {
        return clickListener;
    }

}