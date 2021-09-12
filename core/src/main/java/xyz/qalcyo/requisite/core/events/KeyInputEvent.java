package xyz.qalcyo.requisite.core.events;

import xyz.qalcyo.simpleeventbus.Cancellable;
import xyz.qalcyo.simpleeventbus.Event;

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