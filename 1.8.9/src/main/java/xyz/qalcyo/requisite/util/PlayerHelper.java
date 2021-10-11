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

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import xyz.qalcyo.requisite.core.util.IPlayerHelper;

import java.util.UUID;

public class PlayerHelper implements IPlayerHelper {

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

    public GameProfile getGameProfile() {
        return Minecraft.getMinecraft().getSession().getProfile();
    }

    public String getUsername() {
        return Minecraft.getMinecraft().getSession().getUsername();
    }

    public UUID getUuid() {
        return Minecraft.getMinecraft().getSession().getProfile().getId();
    }

}