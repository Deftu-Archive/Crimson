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

class Notification @JvmOverloads constructor(
    var title: String,
    var description: String,
    var colour: NotificationColour?,
    val duration: Int,
    val clickListener: Consumer<Notification>? = null
) {

    val data: NotificationData = NotificationData(0F, false, false)

    @JvmOverloads
    constructor(title: String, description: String, colour: NotificationColour? = null, clickListener: Consumer<Notification>? = null) : this(title, description, colour, -1, clickListener)
    constructor(title: String, description: String, duration: Int) : this(title, description, null, duration, null)
    constructor(title: String, description: String, clickListener: Consumer<Notification>?) : this(title, description, null, clickListener)
    constructor(title: String, description: String, duration: Int, clickListener: Consumer<Notification>?) : this(title, description, null, duration, clickListener)

    fun clone() =
        Notification(title, description, colour, duration, clickListener)

    fun click() =
        clickListener?.accept(this)

    fun close() {
        data.closing = true
    }

}