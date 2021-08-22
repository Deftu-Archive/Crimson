package xyz.matthewtgm.requisite.core.events;

import xyz.matthewtgm.simpleeventbus.Event;

public class RenderHudEvent extends Event {
    public final float partialTicks;
    public RenderHudEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}