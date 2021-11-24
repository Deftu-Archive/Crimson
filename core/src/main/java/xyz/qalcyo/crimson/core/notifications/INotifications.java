/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core.notifications;

import java.util.function.Consumer;

public interface INotifications {
    default void push(String title, String description, NotificationColour colour, int duration, Consumer<Notification> clickListener) {
        push(new Notification(title, description, colour, duration, clickListener));
    }
    default void push(String title, String description, Consumer<Notification> clickListener) {
        push(new Notification(title, description, clickListener));
    }
    default void push(String title, String description, int duration) {
        push(new Notification(title, description, duration));
    }
    default void push(String title, String description, NotificationColour colour) {
        push(new Notification(title, description, colour));
    }
    default void push(String title, String description, NotificationColour colour, int duration) {
        push(new Notification(title, description, colour, duration));
    }
    default void push(String title, String description, int duration, Consumer<Notification> clickListener) {
        push(new Notification(title, description, duration, clickListener));
    }
    default void push(String title, String description, NotificationColour colour, Consumer<Notification> clickListener) {
        push(new Notification(title, description, colour, clickListener));
    }
    default void push(String title, String description) {
        push(new Notification(title, description));
    }
    void push(Notification notification);

    void render(float ticks);
}