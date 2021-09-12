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

package xyz.qalcyo.requisite.util;

import gg.essential.universal.wrappers.UPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import xyz.qalcyo.requisite.core.util.ChatColour;
import xyz.qalcyo.requisite.core.util.IChatHelper;

public class ChatHelper implements IChatHelper {

    public void send(String prefix, String message) {
        if (prefix == null || message == null)
            return;
        if (!UPlayer.hasPlayer())
            return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix).appendText(ChatColour.RESET + " ").appendText(message));
    }

    public void send(String message) {
        if (message == null)
            return;
        if (!UPlayer.hasPlayer())
            return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }

}