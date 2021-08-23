package xyz.matthewtgm.requisite.core.events;

import xyz.matthewtgm.simpleeventbus.Event;

public class KeyInputEvent extends Event {
    public final int keyCode;
    public KeyInputEvent(int keyCode) {
        this.keyCode = keyCode;
    }
}