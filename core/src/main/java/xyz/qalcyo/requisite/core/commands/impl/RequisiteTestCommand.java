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

package xyz.qalcyo.requisite.core.commands.impl;

import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.commands.annotations.Command;
import xyz.qalcyo.requisite.core.data.ColourRGB;
import xyz.qalcyo.requisite.core.notifications.NotificationColour;

@Command(
        name = "requisitetest",
        generateTabCompletions = true
)
public class RequisiteTestCommand {

    private final RequisiteAPI requisite;

    public RequisiteTestCommand(RequisiteAPI requisite) {
        this.requisite = requisite;
    }

    @Command.Argument(name = "1")
    public void one() {
        requisite.getNotifications().push("Test one", "This is the test one notification.");
    }

    @Command.Argument(name = "2")
    public void two() {
        requisite.getNotifications().push(
                "Test two",
                "This is the test two notification, now with colour.",
                new NotificationColour(
                        new ColourRGB(255, 165, 190),
                        new ColourRGB(255, 156, 132)
                )
        );
    }

}