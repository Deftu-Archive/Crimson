package xyz.matthewtgm.requisite.core;

import xyz.matthewtgm.mango.collections.Pair;
import xyz.matthewtgm.mango.collections.impl.ImmutablePair;
import xyz.matthewtgm.requisite.core.events.*;
import xyz.matthewtgm.simpleeventbus.Event;

public final class RequisiteEventManager {

    private final IRequisite requisite;

    public RequisiteEventManager(IRequisite requisite) {
        this.requisite = requisite;
    }

    public void handleHudRender(float partialTicks) {
        requisite.getManager().getEventBus().call(new RenderHudEvent(partialTicks));
    }

    public void handleTick() {
        requisite.getManager().getEventBus().call(new TickEvent());
    }

    public void handleRenderTick(float partialTicks) {
        requisite.getManager().getEventBus().call(new RenderTickEvent(partialTicks));
    }

    public Pair<String, Boolean> handleChatMessageSent(String message) {
        SendChatMessageEvent event = new SendChatMessageEvent(message);
        requisite.getManager().getEventBus().call(event);
        return new ImmutablePair<>(event.message, event.isCancelled());
    }

    public boolean handleChatMessageReceived(String message, byte type) {
        return parseCancellable(new ChatMessageReceivedEvent(message, type));
    }

    public boolean handleKeyInput(int keyCode, boolean down, boolean repeated) {
        return parseCancellable(new KeyInputEvent(keyCode, down, repeated));
    }

    public void handleWorldLoad() {
        requisite.getManager().getEventBus().call(new WorldLoadEvent());
    }

    private boolean parseCancellable(Event event) {
        requisite.getManager().getEventBus().call(event);
        return event.isCancellable() && event.isCancelled();
    }

}