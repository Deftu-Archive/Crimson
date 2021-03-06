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

package xyz.qalcyo.crimson.core;

import xyz.qalcyo.crimson.core.events.*;
import xyz.qalcyo.mango.collections.Pair;
import xyz.qalcyo.mango.collections.impl.ImmutablePair;
import xyz.qalcyo.eventbus.Event;

/**
 * Adds access to event handling easily.
 */
public final class InternalEventManager {

    private final CrimsonAPI crimson;

    public InternalEventManager() {
        this.crimson = CrimsonAPI.retrieveInstance();
    }

    /**
     * Handles HUD rendering.
     */
    public void handleHudRender(float partialTicks) {
        crimson.getEventBus().post(new RenderHudEvent(partialTicks));
    }

    /**
     * Handles client ticks.
     */
    public void handleTick() {
        crimson.getEventBus().post(new TickEvent());
    }

    /**
     * Handles render ticks.
     */
    public void handleRenderTick(float partialTicks) {
        crimson.getEventBus().post(new RenderTickEvent(partialTicks));
    }

    /**
     * Handles chat messages being sent.
     */
    public Pair<String, Boolean> handleChatMessageSent(String message) {
        SendChatMessageEvent event = new SendChatMessageEvent(message);
        crimson.getEventBus().post(event);
        return new ImmutablePair<>(event.message, event.isCancelled());
    }

    /**
     * Handles chat messages being received.
     */
    public boolean handleChatMessageReceived(String message, byte type) {
        return parseCancellable(new ChatMessageReceivedEvent(message, type));
    }

    /**
     * Handles key inputs.
     */
    public boolean handleKeyInput(int keyCode, boolean down, boolean repeated) {
        return parseCancellable(new KeyInputEvent(keyCode, down, repeated));
    }

    /**
     * Handles world loading.
     */
    public void handleWorldLoad() {
        crimson.getEventBus().post(new WorldLoadEvent());
    }

    /**
     * Calls and returns the value of an event's cancel status.
     *
     * @param event The event to parse.
     * @return Whether the event is cancellable and is cancelled or not.
     */
    private boolean parseCancellable(Event event) {
        crimson.getEventBus().post(event);
        return event.isCancellable() && event.isCancelled();
    }

}