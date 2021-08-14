package xyz.matthewtgm.requisite.core.hypixel.locraw;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.parser.JsonParser;
import xyz.matthewtgm.json.util.JsonHelper;
import xyz.matthewtgm.requisite.events.ChatMessageReceivedEvent;
import xyz.matthewtgm.requisite.events.LocrawReceivedEvent;
import xyz.matthewtgm.requisite.util.ServerHelper;

import java.util.concurrent.TimeUnit;

public class HypixelLocrawManager {

    private HypixelLocraw locraw;
    private boolean allowCancel;

    public HypixelLocrawManager() {
        ForgeHelper.unregisterEventListener(this);
    }

    @SubscribeEvent
    protected void onWorldLoad(WorldEvent.Load event) {
        locraw = null;
        allowCancel = false;

        if (ServerHelper.hypixel()) {
            enqueueUpdate(1000);
        }
    }

    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGHEST)
    protected void onChatMessageReceived(ChatMessageReceivedEvent event) {
        String stripped = StringHelper.removeColourCodes(event.component.getUnformattedText());
        if (JsonHelper.isValidJson(stripped)) {
            JsonObject parsed = JsonParser.parse(stripped).getAsJsonObject();

            if (parsed.hasKey("server") && parsed.getAsString("server").contains("limbo")) {
                forceUpdate(HypixelLocraw.LIMBO);
                event.setCanceled(true);
            }

            forceUpdate(new HypixelLocraw(
                    parsed.get("server"),
                    parsed.get("mode"),
                    parsed.get("map"),
                    parsed.get("gametype")
            ));
            event.setCanceled(true);
        }
    }

    public void enqueueUpdate(int interval) {
        if (!allowCancel) {
            allowCancel = true;
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw"), interval, TimeUnit.MILLISECONDS);
        }
    }

    private void forceUpdate(HypixelLocraw locraw) {
        this.locraw = locraw;
        ForgeHelper.postEvent(new LocrawReceivedEvent(locraw));
        allowCancel = false;
    }

    public HypixelLocraw getLocraw() {
        return locraw;
    }

}