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

package xyz.deftu.requisite.core.hypixel.locraw;

import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.json.parser.JsonParser;
import xyz.deftu.json.util.JsonHelper;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.events.ChatMessageReceivedEvent;
import xyz.deftu.requisite.core.events.WorldLoadEvent;
import xyz.deftu.requisite.core.hypixel.HypixelHelper;
import xyz.deftu.requisite.core.hypixel.events.LocrawReceivedEvent;

import java.util.concurrent.TimeUnit;

public class HypixelLocrawManager {

    private final IRequisite requisite;
    private final HypixelHelper hypixelManager;

    private HypixelLocraw locraw;
    private boolean allowCancel;

    public HypixelLocrawManager(IRequisite requisite, HypixelHelper hypixelManager) {
        this.requisite = requisite;
        this.hypixelManager = hypixelManager;
        requisite.getEventBus().register(WorldLoadEvent.class, this::onWorldLoad);
        requisite.getEventBus().register(ChatMessageReceivedEvent.class, this::onChatMessageReceived);
    }

    protected void onWorldLoad(WorldLoadEvent event) {
        locraw = null;
        allowCancel = false;

        if (hypixelManager.isOnHypixel()) {
            enqueueUpdate(1000);
        }
    }

    protected void onChatMessageReceived(ChatMessageReceivedEvent event) {
        String stripped = requisite.getStringHelper().removeFormattingCodes(event.message);
        if (JsonHelper.isValidJson(stripped)) {
            JsonObject parsed = JsonParser.parse(stripped).getAsJsonObject();

            if (parsed.hasKey("server") && parsed.getAsString("server").contains("limbo")) {
                forceUpdate(HypixelLocraw.LIMBO);
                event.cancel();
            }

            forceUpdate(new HypixelLocraw(
                    parsed.get("server"),
                    parsed.get("mode"),
                    parsed.get("map"),
                    parsed.get("gametype")
            ));
            event.cancel();
        }
    }

    public void enqueueUpdate(int interval) {
        if (!allowCancel) {
            allowCancel = true;
            requisite.getMultithreading().schedule(() -> requisite.getMessageQueue().queue("/locraw"), interval, TimeUnit.MILLISECONDS);
        }
    }

    private void forceUpdate(HypixelLocraw locraw) {
        this.locraw = locraw;
        requisite.getEventBus().call(new LocrawReceivedEvent(locraw));
        allowCancel = false;
    }

    public HypixelLocraw getLocraw() {
        return locraw;
    }

}