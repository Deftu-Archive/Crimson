package xyz.deftu.requisite.core.events;

import xyz.deftu.simpleeventbus.Event;

public class RenderHudEvent extends Event {
    public final float partialTicks;
    public RenderHudEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}