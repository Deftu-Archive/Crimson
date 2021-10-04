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

package xyz.qalcyo.requisite.core.integration.hypixel.locraw;

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.parser.JsonParser;
import xyz.qalcyo.json.util.JsonHelper;
import xyz.qalcyo.mango.Multithreading;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.events.ChatMessageReceivedEvent;
import xyz.qalcyo.requisite.core.events.WorldLoadEvent;
import xyz.qalcyo.requisite.core.integration.hypixel.HypixelHelper;
import xyz.qalcyo.requisite.core.integration.hypixel.events.LocrawReceivedEvent;

import java.util.concurrent.TimeUnit;

public class HypixelLocrawManager {

    private final RequisiteAPI requisite;
    private final HypixelHelper hypixelManager;

    private HypixelLocraw locraw;
    private boolean allowCancel;

    public HypixelLocrawManager(RequisiteAPI requisite, HypixelHelper hypixelManager) {
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
            JsonElement parsed = JsonParser.parse(stripped);
            if (parsed.isJsonObject()) {
                JsonObject object = parsed.getAsJsonObject();
                if (object.hasKey("server") && object.getAsString("server").contains("limbo")) {
                    forceUpdate(HypixelLocraw.LIMBO);
                    event.cancel();
                }

                forceUpdate(new HypixelLocraw(
                        object.get("server"),
                        object.get("mode"),
                        object.get("map"),
                        object.get("gametype")
                ));
                event.cancel();
            }
        }
    }

    public void enqueueUpdate(int interval) {
        if (!allowCancel) {
            allowCancel = true;
            Multithreading.schedule(() -> requisite.getMessageQueue().queue("/locraw"), interval, TimeUnit.MILLISECONDS);
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