/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
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

package xyz.qalcyo.requisite.core.notifications

import java.util.function.Consumer

interface INotifications {

    fun push(title: String, description: String, colour: NotificationColour, duration: Int, clickListener: Consumer<Notification>) {
        push(Notification(title, description, colour, duration, clickListener))
    }
    fun push(title: String, description: String, clickListener: Consumer<Notification>) {
        push(Notification(title, description, clickListener))
    }
    fun push(title: String, description: String, duration: Int) {
        push(Notification(title, description, duration))
    }
    fun push(title: String, description: String, colour: NotificationColour) {
        push(Notification(title, description, colour))
    }
    fun push(title: String, description: String, colour: NotificationColour, duration: Int) {
        push(Notification(title, description, colour, duration))
    }
    fun push(title: String, description: String, duration: Int, clickListener: Consumer<Notification>) {
        push(Notification(title, description, duration, clickListener))
    }
    fun push(title: String, description: String, colour: NotificationColour, clickListener: Consumer<Notification>) {
        push(Notification(title, description, colour, clickListener))
    }
    fun push(title: String, description: String) {
        push(Notification(title, description))
    }

    fun push(notification: Notification)
    fun render(ticks: Float)
}