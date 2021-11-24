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

package xyz.qalcyo.crimson.core.commands.impl;

import xyz.qalcyo.crimson.core.CrimsonAPI;
import xyz.qalcyo.crimson.core.commands.annotations.Command;
import xyz.qalcyo.crimson.core.data.ColourRGB;
import xyz.qalcyo.crimson.core.notifications.NotificationColour;
import xyz.qalcyo.crimson.core.util.ChatColour;

@Command(
        name = "crimsontest",
        generateTabCompletions = true
)
public class CrimsonTestCommand {

    private final CrimsonAPI crimson;

    public CrimsonTestCommand(CrimsonAPI crimson) {
        this.crimson = crimson;
    }

    @Command.Default
    public void execute() {
        crimson.getChatHelper().send("Invalid argument.");
    }

    @Command.Argument(name = "1")
    public void one() {
        crimson.getNotifications().push(
                "Test one",
                "This is the test one notification."
        );
    }

    @Command.Argument(name = "2")
    public void two() {
        crimson.getNotifications().push(
                "Test two",
                "This is the test two notification, using the alternative colour.",
                NotificationColour.ALTERNATIVE
        );
    }

    @Command.Argument(name = "3")
    public void three() {
        crimson.getNotifications().push(
                "Test three",
                "This is the test three notification, now using custom colours.",
                new NotificationColour(
                        new ColourRGB(255, 165, 190),
                        new ColourRGB(255, 156, 132)
                )
        );
    }

    @Command.Argument(name = "4")
    public void four() {
        crimson.getChatHelper().send("Your session ID is: " + ChatColour.BOLD + crimson.getCrimsonSocket().getSessionId());
    }

    @Command.Argument(name = "5")
    public void five() {
        crimson.getChatHelper().send("Locraw: " + ChatColour.BOLD + crimson.getHypixelHelper().getLocrawManager().getLocraw().toString());
    }

    @Command.Argument(name = "6")
    public void six() {
        crimson.getLogger().info(crimson.getBridge().getMinecraftBridge().getCrimsonModList());
    }

}