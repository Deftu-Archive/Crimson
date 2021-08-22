package xyz.matthewtgm.requisite.core.events;

import xyz.matthewtgm.simpleeventbus.Event;

public class RenderTickEvent extends Event {
    public final float partialTicks;
    public RenderTickEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}