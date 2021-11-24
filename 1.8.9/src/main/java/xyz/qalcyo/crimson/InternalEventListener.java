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

package xyz.qalcyo.crimson;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import xyz.qalcyo.crimson.core.CrimsonAPI;
import xyz.qalcyo.crimson.core.IEventListener;

public class InternalEventListener implements IEventListener {

    private final CrimsonAPI crimson;

    public InternalEventListener(CrimsonAPI crimson) {
        this.crimson = crimson;
        MinecraftForge.EVENT_BUS.register(this);
    }
    

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        if (event.type == 0 || event.type == 1) {
            event.setCanceled(crimson.getInternalEventManager().handleChatMessageReceived(event.message.getUnformattedText(), event.type));
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        crimson.getInternalEventManager().handleKeyInput(Keyboard.getEventKey(), Keyboard.getEventKeyState(), Keyboard.isRepeatEvent());
    }

    @SubscribeEvent
    public void onGameOverlayRendered(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            crimson.getInternalEventManager().handleHudRender(event.partialTicks);
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        crimson.getInternalEventManager().handleTick();
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        crimson.getInternalEventManager().handleRenderTick(event.renderTickTime);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        crimson.getInternalEventManager().handleWorldLoad();
    }

    @SubscribeEvent
    public void onMainMenuOpened(GuiScreenEvent.InitGuiEvent event) {
        if (event.gui instanceof GuiMainMenu && !crimson.getConfigManager().getOnboarding().isPrompted()) {
            //Crimson.getInstance().getGuiHelper().open(new StartupMenu());
        }
    }

}