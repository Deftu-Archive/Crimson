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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.events.TickEvent;
import xyz.qalcyo.requisite.core.util.IGuiHelper;

public class GuiHelper implements IGuiHelper<GuiScreen> {

    private GuiScreen awaitingDisplay = new GuiNull();

    public GuiHelper() {
        Requisite.getInstance().getEventBus().register(TickEvent.class, this::onClientTick);
    }

    public void open(GuiScreen screen) {
        awaitingDisplay = screen;
    }

    public boolean isGuiPresent() {
        return Minecraft.getMinecraft().currentScreen != null;
    }

    public GuiScreen getAwaiting() {
        return awaitingDisplay;
    }

    protected void onClientTick(TickEvent event) {
        if (!(awaitingDisplay instanceof GuiNull)) {
            Minecraft.getMinecraft().displayGuiScreen(awaitingDisplay);
            awaitingDisplay = new GuiNull();
        }
    }

    private static class GuiNull extends GuiScreen {
    }

}