package xyz.deftu.requisite.core.events;

import xyz.deftu.simpleeventbus.Event;

public class RenderTickEvent extends Event {
    public final float partialTicks;
    public RenderTickEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}