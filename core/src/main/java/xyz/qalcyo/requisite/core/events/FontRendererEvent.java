package xyz.qalcyo.requisite.core.events;

import xyz.qalcyo.simpleeventbus.Event;

public class FontRendererEvent extends Event {
    public String text;
    public FontRendererEvent(String text) {
        this.text = text;
    }
    public static class RenderEvent extends FontRendererEvent {
        public float x;
        public float y;
        public int colour;
        public boolean dropShadow;
        public RenderEvent(String text, float x, float y, int colour, boolean dropShadow) {
            super(text);
            this.x = x;
            this.y = y;
            this.colour = colour;
            this.dropShadow = dropShadow;
        }
    }
    public static class WidthGottenEvent extends FontRendererEvent {
        public WidthGottenEvent(String text) {
            super(text);
        }
    }
}
