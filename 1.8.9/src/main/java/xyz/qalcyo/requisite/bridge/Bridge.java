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
import xyz.qalcyo.requisite.core.bridge.requisite.IRenderingBridge;
import xyz.qalcyo.requisite.core.bridge.requisite.ISocketBridge;
import xyz.qalcyo.requisite.bridge.requisite.SocketBridge;
import xyz.qalcyo.requisite.core.bridge.requisite.RenderingBridge;

public class Bridge implements IBridge {

    private CommandBridge commandBridge;
    private SocketBridge socketBridge;
    private RenderingBridge renderingBridge;
    private MinecraftBridge minecraftBridge;

    public void initialize() {
        commandBridge = new CommandBridge();
        socketBridge = new SocketBridge();
        renderingBridge = new RenderingBridge();
        minecraftBridge = new MinecraftBridge();
    }

    public CommandBridge getCommandBridge() {
        return commandBridge;
    }

    public SocketBridge getSocketBridge() {
        return socketBridge;
    }

    public IRenderingBridge getRenderingBridge() {
        return renderingBridge;
    }

    public MinecraftBridge getMinecraftBridge() {
        return minecraftBridge;
    }

}