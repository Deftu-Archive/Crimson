package xyz.qalcyo.requisite.core.events;

import xyz.qalcyo.simpleeventbus.Event;

public class RenderTickEvent extends Event {
    public final float partialTicks;
    public RenderTickEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}