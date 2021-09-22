package xyz.qalcyo.requisite.core;

import xyz.qalcyo.requisite.core.events.*;
import xyz.qalcyo.mango.collections.Pair;
import xyz.qalcyo.mango.collections.impl.ImmutablePair;
import xyz.qalcyo.simpleeventbus.Event;

public final class RequisiteEventManager {

    private final IRequisite requisite;

    public RequisiteEventManager(IRequisite requisite) {
        this.requisite = requisite;
    }

    public void handleHudRender(float partialTicks) {
        requisite.getEventBus().call(new RenderHudEvent(partialTicks));
    }

    public void handleTick() {
        requisite.getEventBus().call(new TickEvent());
    }

    public void handleRenderTick(float partialTicks) {
        requisite.getEventBus().call(new RenderTickEvent(partialTicks));
    }

    public Pair<String, Boolean> handleChatMessageSent(String message) {
        SendChatMessageEvent event = new SendChatMessageEvent(message);
        requisite.getEventBus().call(event);
        return new ImmutablePair<>(event.message, event.isCancelled());
    }

    public boolean handleChatMessageReceived(String message, byte type) {
        return parseCancellable(new ChatMessageReceivedEvent(message, type));
    }

    public boolean handleKeyInput(int keyCode, boolean down, boolean repeated) {
        return parseCancellable(new KeyInputEvent(keyCode, down, repeated));
    }

    public void handleWorldLoad() {
        requisite.getEventBus().call(new WorldLoadEvent());
    }

    private boolean parseCancellable(Event event) {
        requisite.getEventBus().call(event);
        return event.isCancellable() && event.isCancelled();
    }

}