package xyz.matthewtgm.requisite.core.notifications;

import xyz.matthewtgm.requisite.data.ColourRGB;

public class NotificationColour {
    public final ColourRGB background;
    public final ColourRGB foreground;
    public NotificationColour(ColourRGB background, ColourRGB foreground) {
        this.background = background;
        this.foreground = foreground;
    }
}