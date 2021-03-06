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

package xyz.qalcyo.crimson.core.integration.hypixel.locraw;

import org.apache.logging.log4j.Logger;
import xyz.qalcyo.eventbus.EventPriority;
import xyz.qalcyo.eventbus.SubscribeEvent;
import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.parser.JsonParser;
import xyz.qalcyo.json.util.JsonHelper;
import xyz.qalcyo.mango.Multithreading;
import xyz.qalcyo.crimson.core.CrimsonAPI;
import xyz.qalcyo.crimson.core.events.ChatMessageReceivedEvent;
import xyz.qalcyo.crimson.core.events.TickEvent;
import xyz.qalcyo.crimson.core.events.WorldLoadEvent;
import xyz.qalcyo.crimson.core.integration.hypixel.HypixelHelper;
import xyz.qalcyo.crimson.core.integration.hypixel.events.LocrawReceivedEvent;

import java.util.concurrent.TimeUnit;

public class HypixelLocrawManager {

    private final CrimsonAPI crimson;
    private final HypixelHelper hypixelHelper;
    private final Logger logger;

    private int tickTicker = 0;

    private HypixelLocraw locraw;
    private boolean checked;
    private boolean allowCancel;

    private int limboLoop;

    public HypixelLocrawManager(HypixelHelper hypixelHelper) {
        this.crimson = CrimsonAPI.retrieveInstance();
        this.hypixelHelper = hypixelHelper;
        this.logger = crimson.getUniversalLogger().create();
        crimson.getEventBus().register(this);
    }

    @SubscribeEvent
    private void onClientTick(TickEvent event) {
        tickTicker++;
        if (tickTicker % 20 == 0 && hypixelHelper.isOnHypixel() && !checked) {
            enqueueUpdate(1000);
            checked = true;
        }
    }

    @SubscribeEvent
    private void onWorldLoad(WorldLoadEvent event) {
        locraw = null;
        checked = false;
        allowCancel = false;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private void onChatMessageReceived(ChatMessageReceivedEvent event) {
        if (allowCancel) {
            String stripped = crimson.getStringHelper().removeFormattingCodes(event.message);
            if (JsonHelper.isValidJson(stripped)) {
                JsonElement parsed = JsonParser.parse(stripped);
                if (parsed.isJsonObject()) {
                    JsonObject object = parsed.getAsJsonObject();
                    if (object.hasKey("server") && object.getAsString("server").contains("limbo")) {
                        if (limboLoop > 3) {
                            forceUpdate(HypixelLocraw.LIMBO);
                            event.cancel();
                            return;
                        }

                        allowCancel = false;
                        checked = false;
                        limboLoop++;

                        event.cancel();
                        return;
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
    }

    public void enqueueUpdate(int interval) {
        if (!allowCancel) {
            allowCancel = true;
            Multithreading.schedule(() -> {
                logger.debug("Sending locraw command to Hypixel.");
                crimson.getMessageQueue().queue("/locraw");
            }, interval, TimeUnit.MILLISECONDS);
        }
    }

    private void forceUpdate(HypixelLocraw locraw) {
        this.locraw = locraw;
        crimson.getEventBus().post(new LocrawReceivedEvent(locraw));
        allowCancel = false;
    }

    public HypixelLocraw getLocraw() {
        return locraw;
    }

}