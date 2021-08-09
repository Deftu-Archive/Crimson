package xyz.matthewtgm.requisite.hud;

import lombok.Getter;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    @Getter private final List<HudElementBase> elements = new ArrayList<>();

    public void register(HudElementBase element) {
        elements.add(element);
    }

    public void openEditor() {

    }

    public <T extends HudElementBase> T getElementInstance(String id) {
        for (HudElementBase element : elements) {
            if (element.getId().equals(id)) {
                return (T) element;
            }
        }
        return null;
    }

    public <T extends HudElementBase> T getElementInstance(Class<T> clazz) {
        for (HudElementBase element : elements) {
            if (element.getClass().isAssignableFrom(clazz)) {
                return (T) element;
            }
        }
        return null;
    }

    public boolean isHudRegistered(String id) {
        for (HudElementBase element : elements) {
            if (element.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            for (HudElementBase element : elements) {
                if (element.isToggled()) {
                    element.render(event.partialTicks);
                }
            }
        }
    }

}