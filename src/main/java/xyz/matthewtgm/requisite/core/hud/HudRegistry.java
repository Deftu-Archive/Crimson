package xyz.matthewtgm.requisite.core.hud;

import xyz.matthewtgm.json.entities.JsonObject;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.data.IScreenPosition;
import xyz.matthewtgm.requisite.core.events.RenderHudEvent;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.IConfigurable;
import xyz.matthewtgm.requisite.core.settings.RequisiteSettingsParser;
import xyz.matthewtgm.simpleeventbus.EventSubscriber;
import xyz.matthewtgm.tgmconfig.Configuration;
import xyz.matthewtgm.tgmconfig.Subconfiguration;
import xyz.matthewtgm.tgmconfig.settings.BaseSetting;

import java.util.ArrayList;
import java.util.List;

public class HudRegistry implements IConfigurable {

    private final IRequisite requisite;
    private final RequisiteSettingsParser settingsParser;

    private final List<HudElement> elements = new ArrayList<>();

    public HudRegistry(IRequisite requisite) {
        this.requisite = requisite;
        this.settingsParser = new RequisiteSettingsParser(requisite);
        requisite.getManager().getEventBus().register(this);
    }

    public void register(HudElement element) {
        if (!elements.contains(element)) {
            element.initialize(requisite);
            elements.add(element);

            load(element);
            save(element);
        }
    }

    public void unregister(String name) {
        elements.stream().filter(theElement -> theElement.getName().equals(name)).findFirst().ifPresent(elements::remove);
    }

    public void unregister(HudElement element) {
        unregister(element.getName());
    }

    public void save(ConfigurationManager configurationManager) {
        for (HudElement element : elements) {
           save(element);
        }
    }

    public void save(HudElement element) {
        Configuration configuration = mainConfig();
        if (!configuration.hasKey("hud"))
            configuration.createSubconfiguration("hud");
        Subconfiguration hudConfiguration = configuration.getSubconfiguration("hud");

        JsonObject elementSettings = new JsonObject();
        for (BaseSetting<?> setting : element.getSettings())
            elementSettings.add(setting.jsonKey(), setting.get());
        hudConfiguration.add(element.getJsonKey(), elementSettings);

        configuration.save();
    }

    public void load(ConfigurationManager configurationManager) {
        for (HudElement element : elements) {
            load(element);
        }
    }

    public void load(HudElement element) {
        Configuration configuration = mainConfig();
        if (!configuration.hasKey("hud"))
            configuration.createSubconfiguration("hud");
        Subconfiguration hudConfiguration = configuration.getSubconfiguration("hud");

        if (!hudConfiguration.hasKey(element.getJsonKey()))
            return;

        JsonObject elementSettings = hudConfiguration.getAsJsonObject(element.getJsonKey());
        for (BaseSetting setting : element.getSettings()) {
            if (elementSettings.hasKey(setting.jsonKey())) {
                System.out.println(settingsParser.parse(elementSettings.get(setting.jsonKey())).toString());
                setting.set(settingsParser.parse(elementSettings.get(setting.jsonKey())));
            }
        }
    }

    public Configuration mainConfig() {
        return requisite.getManager().getConfigurationManager().getConfiguration();
    }

    public void render(float partialTicks) {
        for (HudElement element : elements) {
            if (element.toggleSetting.get()) {
                IScreenPosition position = element.positionSetting.get();
                element.render(element.positionSetting.get(), partialTicks);
            }
        }
    }

    @EventSubscriber
    private void onHudRender(RenderHudEvent event) {
        render(event.partialTicks);
    }

    public List<HudElement> getElements() {
        return elements;
    }

}