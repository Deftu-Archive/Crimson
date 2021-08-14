package xyz.matthewtgm.requisite;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.matthewtgm.requisite.core.events.ChatMessageReceivedEvent;

public class RequisiteEventListener {

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        if (event.type == 0 || event.type == 1) {
            ChatMessageReceivedEvent chatMessageReceivedEvent = new ChatMessageReceivedEvent(event.message.getUnformattedText(), event.type);
            event.setCanceled(chatMessageReceivedEvent.isCancelled());
        }
    }

}