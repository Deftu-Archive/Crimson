package xyz.matthewtgm.requisite.hud.types;

import lombok.Getter;
import xyz.matthewtgm.requisite.data.ScreenPosition;
import xyz.matthewtgm.requisite.hud.HudElementBase;

public class HudElementText extends HudElementBase {

    @Getter private String text;
    @Getter private int colour;

    public HudElementText(String name, String id, String description, String text, int colour) {
        super(name, id, description);
        this.text = text;
        this.colour = colour;
    }

    public HudElementText(String name, String id, String text, int colour) {
        super(name, id);
        this.text = text;
        this.colour = colour;
    }

    public HudElementText(String name, String text, int colour) {
        super(name);
        this.text = text;
        this.colour = colour;
    }

    public HudElementText(String name, String text) {
        super(name);
        this.text = text;
    }

    public HudElementText(String name, int colour) {
        super(name);
        this.colour = colour;
    }

    public final void render(float partialTicks) {
        this.width = EnhancedFontRenderer.getWidth(text);
        this.height = EnhancedFontRenderer.getFontHeight();

        ScreenPosition position = this.position.get();
        EnhancedFontRenderer.drawText(text, position.getX(), position.getY(), colour);
    }

    public <T extends HudElementText> T setText(String text) {
        this.text = text;
        return (T) this;
    }

    public <T extends HudElementText> T setColour(int colour) {
        this.colour = colour;
        return (T) this;
    }

}