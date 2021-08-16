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

    void render(float ticks);
}