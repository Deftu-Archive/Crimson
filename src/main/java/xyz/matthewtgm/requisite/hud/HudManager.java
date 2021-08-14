package xyz.matthewtgm.requisite.hud;

import lombok.Getter;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.requisite.configuration.settings.PositionSetting;
import xyz.matthewtgm.requisite.data.ScreenPosition;
import xyz.matthewtgm.requisite.gui.RequisiteHudMenu;
import xyz.matthewtgm.requisite.util.global.GlobalMinecraft;
import xyz.matthewtgm.tgmconfig.Configuration;
import xyz.matthewtgm.tgmconfig.Subconfiguration;
import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    @Getter private final List<HudElementBase> elements = new ArrayList<>();
    @Getter private final Configuration configuration;
    
    public HudManager(Configuration configuration) {
        this.configuration = configuration;
    }

    public void register(HudElementBase element) {
        elements.add(element);
        load(element);
        save(element);
    }

    public void openEditor() {
        GuiHelper.open(new RequisiteHudMenu());
    }
    
    public void save(HudElementBase element) {
        Subconfiguration elementConfiguration;
        if (!configuration.hasKey(element.getId()))
            configuration.createSubconfiguration(element.getId());
        elementConfiguration = configuration.getSubconfiguration(element.getId());
        for (BaseSetting<?> setting : element.getSettings()) {
            if (handlePositionSettingSave(elementConfiguration, setting))
                continue;
            elementConfiguration.add(setting.jsonKey(), setting.get());
        }
    }
    
    public void load(HudElementBase element) {
        if (!configuration.hasKey(element.getId()))
            return;
        Subconfiguration elementConfiguration = configuration.getSubconfiguration(element.getId());
        for (BaseSetting setting : element.getSettings()) {
            if (!elementConfiguration.hasKey(setting.jsonKey()))
                continue;
            if (handlePositionSettingLoad(elementConfiguration, setting))
                continue;
            setting.set(elementConfiguration.get(setting.jsonKey()).getAsJsonPrimitive().getValue());
        }
    }

    public void save() {
        for (HudElementBase element : elements) {
            save(element);
        }
        configuration.save();
    }

    public void load() {
        configuration.sync();
        for (HudElementBase element : elements) {
            load(element);
        }
    }

    private boolean handlePositionSettingSave(Subconfiguration elementConfiguration, BaseSetting setting) {
        if (!(setting instanceof PositionSetting))
            return false;

        PositionSetting positionSetting = (PositionSetting) setting;
        ScreenPosition position = positionSetting.get();
        elementConfiguration.add(positionSetting.jsonKey(), new JsonObject()
                .add("x", position.getX())
                .add("y", position.getY()));
        return true;
    }

    private boolean handlePositionSettingLoad(Subconfiguration elementConfiguration, BaseSetting setting) {
        if (!(setting instanceof PositionSetting))
            return false;

        Subconfiguration position = elementConfiguration.getSubconfiguration(setting.jsonKey());
        setting.set(ScreenPosition.fromScaled(position.getAsFloat("x"), position.getAsFloat("y")));
        return true;
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

    public void render(float partialTicks) {
        for (HudElementBase element : elements) {
            if (element.isToggled()) {
                element.render(partialTicks);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void render(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL && GlobalMinecraft.getCurrentScreen() == null) {
            render(event.partialTicks);
        }
    }

}