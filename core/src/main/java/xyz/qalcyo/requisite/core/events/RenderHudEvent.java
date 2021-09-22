package xyz.qalcyo.requisite.core.events;

import xyz.qalcyo.simpleeventbus.Event;

public class RenderHudEvent extends Event {
    public final float partialTicks;
    public RenderHudEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}