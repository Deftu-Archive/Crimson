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

package xyz.qalcyo.requisite.util;

import gg.essential.universal.UMinecraft;
import gg.essential.universal.UScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import xyz.qalcyo.requisite.core.util.IServerHelper;

public class ServerHelper implements IServerHelper {

    public void join(String ip, int port) {
        UScreen.displayScreen(new GuiConnecting(UScreen.getCurrentScreen(), UMinecraft.getMinecraft(), ip, port));
    }

    public void join(String ip) {
        join(ip, 25565);
    }

    public String getBrand() {
        if (Minecraft.getMinecraft().thePlayer == null)
            return null;
        return Minecraft.getMinecraft().thePlayer.getClientBrand();
    }

    public boolean isSingleplayer() {
        return Minecraft.getMinecraft().isSingleplayer();
    }

}