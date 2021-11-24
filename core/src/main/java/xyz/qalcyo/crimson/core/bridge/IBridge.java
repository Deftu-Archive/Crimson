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

package xyz.qalcyo.crimson.core.bridge;

import xyz.qalcyo.crimson.core.bridge.minecraft.IMinecraftBridge;
import xyz.qalcyo.crimson.core.bridge.crimson.ICommandBridge;
import xyz.qalcyo.crimson.core.bridge.crimson.IRenderingBridge;
import xyz.qalcyo.crimson.core.bridge.crimson.ISocketBridge;

public interface IBridge extends IBridgeContainer {
    void initialize();
    default void afterInitialize() {
        getCommandBridge().initialize();
        getSocketBridge().initialize();
        getMinecraftBridge().initialize();
    }

    default void start() {
        initialize();
        afterInitialize();
    }

    /* Crimson */
    ICommandBridge getCommandBridge();
    ISocketBridge getSocketBridge();
    IRenderingBridge getRenderingBridge();

    /* Minecraft */
    IMinecraftBridge getMinecraftBridge();
}