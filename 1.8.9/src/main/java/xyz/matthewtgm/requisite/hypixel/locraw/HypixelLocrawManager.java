package xyz.matthewtgm.requisite.hypixel.locraw;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.json.parser.JsonParser;
import xyz.matthewtgm.json.util.JsonHelper;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.events.ChatMessageReceivedEvent;
import xyz.matthewtgm.requisite.hypixel.HypixelManager;
import xyz.matthewtgm.requisite.hypixel.events.LocrawReceivedEvent;
import xyz.matthewtgm.requisite.util.ServerHelper;
import xyz.matthewtgm.simpleeventbus.EventSubscriber;

import java.util.concurrent.TimeUnit;

public class HypixelLocrawManager {

    private final IRequisite requisite;
    private final HypixelManager hypixelManager;

    private HypixelLocraw locraw;
    private boolean allowCancel;

    public HypixelLocrawManager(IRequisite requisite, HypixelManager hypixelManager) {
        this.requisite = requisite;
        this.hypixelManager = hypixelManager;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    protected void onWorldLoad(WorldEvent.Load event) {
        locraw = null;
        allowCancel = false;

        if (hypixelManager.isOnHypixel()) {
            enqueueUpdate(1000);
        }
    }

    @EventSubscriber
    protected void onChatMessageReceived(ChatMessageReceivedEvent event) {
        String stripped = requisite.getManager().getStringHelper().removeFormattingCodes(event.message);
        if (JsonHelper.isValidJson(stripped)) {
            JsonObject parsed = JsonParser.parse(stripped).getAsJsonObject();

            if (parsed.hasKey("server") && parsed.getAsString("server").contains("limbo")) {
                forceUpdate(HypixelLocraw.LIMBO);
                event.setCancelled(true);
            }

            forceUpdate(new HypixelLocraw(
                    parsed.get("server"),
                    parsed.get("mode"),
                    parsed.get("map"),
                    parsed.get("gametype")
            ));
            event.setCancelled(true);
        }
    }

    public void enqueueUpdate(int interval) {
        if (!allowCancel) {
            allowCancel = true;
            requisite.getManager().getMultithreading().schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/locraw"), interval, TimeUnit.MILLISECONDS);
        }
    }

    private void forceUpdate(HypixelLocraw locraw) {
        this.locraw = locraw;
        requisite.getManager().getEventBus().call(new LocrawReceivedEvent(locraw));
        allowCancel = false;
    }

    public HypixelLocraw getLocraw() {
        return locraw;
    }

}