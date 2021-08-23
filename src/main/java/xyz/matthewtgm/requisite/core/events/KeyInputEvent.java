package xyz.matthewtgm.requisite.core.events;

import xyz.matthewtgm.simpleeventbus.Cancellable;
import xyz.matthewtgm.simpleeventbus.Event;

@Cancellable
public class KeyInputEvent extends Event {
    public final int keyCode;
    public final boolean down;
    public final boolean repeated;
    public KeyInputEvent(int keyCode, boolean down, boolean repeated) {
        this.keyCode = keyCode;
        this.down = down;
        this.repeated = repeated;
    }
}