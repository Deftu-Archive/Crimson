package xyz.matthewtgm.requisite.hypixel.events;

import xyz.matthewtgm.requisite.hypixel.locraw.HypixelLocraw;
import xyz.matthewtgm.simpleeventbus.Event;

public class LocrawReceivedEvent extends Event {
    public final HypixelLocraw locraw;
    public LocrawReceivedEvent(HypixelLocraw locraw) {
        this.locraw = locraw;
    }
}