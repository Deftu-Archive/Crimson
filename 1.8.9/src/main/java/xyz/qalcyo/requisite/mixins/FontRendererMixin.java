package xyz.qalcyo.requisite.mixins;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.core.events.FontRendererEvent;

@Mixin(FontRenderer.class)
public class FontRendererMixin {
    @Unique private FontRendererEvent.RenderEvent renderEvent;
    @Unique private FontRendererEvent.WidthGottenEvent widthGottenEvent;

    @Inject(method = "renderString", at = @At("HEAD"), cancellable = true)
    private void onStringRendered(String text, float x, float y, int color, boolean dropShadow, CallbackInfoReturnable<Integer> cir) {
        FontRendererEvent.RenderEvent event = new FontRendererEvent.RenderEvent(text, x, y, color, dropShadow);
        Requisite.getInstance().getEventBus().post(event);
        this.renderEvent = event;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    public String renderString_modifyText(String original) {
        return renderEvent.text;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    public float renderString_modifyX(float original) {
        return renderEvent.x;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    public float renderString_modifyY(float original) {
        return renderEvent.y;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    public int renderString_modifyColor(int original) {
        return renderEvent.colour;
    }

    @ModifyVariable(method = "renderString", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    public boolean renderString_modifyDropShadow(boolean original) {
        return renderEvent.dropShadow;
    }

    @Inject(method = "getStringWidth", at = @At("HEAD"), cancellable = true)
    private void onStringWidthGotten(String text, CallbackInfoReturnable<Integer> cir) {
        FontRendererEvent.WidthGottenEvent event = new FontRendererEvent.WidthGottenEvent(text);
        Requisite.getInstance().getEventBus().post(event);
        this.widthGottenEvent = event;
    }

    @ModifyVariable(method = "getStringWidth", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private String getStringWidth_modifyString(String original) {
        return widthGottenEvent.text;
    }

}
