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

package xyz.qalcyo.requisite.bridge;

import xyz.qalcyo.requisite.bridge.minecraft.MinecraftBridge;
import xyz.qalcyo.requisite.bridge.requisite.CommandBridge;
import xyz.qalcyo.requisite.core.bridge.IBridge;
import xyz.qalcyo.requisite.core.bridge.minecraft.IMinecraftBridge;
import xyz.qalcyo.requisite.core.bridge.requisite.ICommandBridge;
import xyz.qalcyo.requisite.core.bridge.requisite.ISocketBridge;
import xyz.qalcyo.requisite.bridge.requisite.SocketBridge;

public class Bridge implements IBridge {

    private CommandBridge commandBridge;
    private SocketBridge socketBridge;
    private MinecraftBridge minecraftBridge;

    public void preInitialize() {
        commandBridge = new CommandBridge();
        socketBridge = new SocketBridge();
        minecraftBridge = new MinecraftBridge();

        initialize();
    }

    public ICommandBridge getCommandBridge() {
        return commandBridge;
    }

    public ISocketBridge getSocketBridge() {
        return socketBridge;
    }

    public IMinecraftBridge getMinecraftBridge() {
        return minecraftBridge;
    }

}