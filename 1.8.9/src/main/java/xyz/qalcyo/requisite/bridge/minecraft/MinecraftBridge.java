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

package xyz.qalcyo.requisite.bridge.minecraft;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import xyz.qalcyo.requisite.core.bridge.minecraft.IMinecraftBridge;
import xyz.qalcyo.requisite.core.bridge.minecraft.IResourceReloadBridge;

import java.util.UUID;

public class MinecraftBridge implements IMinecraftBridge {

    private GameProfile gameProfile;
    private UUID playerUuid;
    private String playerUsername;

    public void initialize() {
        gameProfile = Minecraft.getMinecraft().getSession().getProfile();
        playerUuid = gameProfile.getId();
        playerUsername = gameProfile.getName();
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public boolean isPlayerPresent() {
        return Minecraft.getMinecraft().thePlayer != null;
    }

    public String getLanguageCode() {
        return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
    }

    public void registerReloadListener(IResourceReloadBridge reloadBridge) {
        IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
        if (resourceManager instanceof IReloadableResourceManager) {
            IReloadableResourceManager reloadableResourceManager = (IReloadableResourceManager) resourceManager;
            reloadableResourceManager.registerReloadListener(self -> {
                reloadBridge.reloadResources();
            });
        }
    }

}