package xyz.deftu.requisite.core.hud;

import xyz.deftu.json.entities.JsonObject;
import xyz.deftu.requisite.core.files.ConfigurationManager;
import xyz.deftu.requisite.core.files.IConfigurable;
import xyz.deftu.simpleconfig.Configuration;
import xyz.deftu.simpleconfig.Subconfiguration;
import xyz.deftu.simpleconfig.settings.BaseSetting;
import xyz.deftu.requisite.core.IRequisite;
import xyz.deftu.requisite.core.events.RenderHudEvent;

import java.util.ArrayList;
import java.util.List;

public class HudRegistry implements IConfigurable {

    private final IRequisite requisite;
    //private final RequisiteSettingsParser settingsParser;

    private final List<HudElement> elements = new ArrayList<>();

    public HudRegistry(IRequisite requisite) {
        this.requisite = requisite;
        //this.settingsParser = new RequisiteSettingsParser(requisite);
        requisite.getEventBus().register(RenderHudEvent.class, this::onHudRender);
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

        if (!hudConfiguration.hasKey(element.getJsonKey())) {
            return;
        }

        JsonObject elementSettings = hudConfiguration.getAsJsonObject(element.getJsonKey());
        for (BaseSetting setting : element.getSettings()) {
            if (elementSettings.hasKey(setting.jsonKey())) {
                //System.out.println(settingsParser.parse(elementSettings.get(setting.jsonKey())).toString());
                //setting.set(settingsParser.parse(elementSettings.get(setting.jsonKey())));
            }
        }
    }

    public Configuration mainConfig() {
        return requisite.getConfigurationManager().getConfiguration();
    }

    public void render(float partialTicks) {
        for (HudElement element : elements) {
            if (element.toggleSetting.get()) {
                //element.render(element.positionSetting.get(), partialTicks);
            }
        }
    }

    private void onHudRender(RenderHudEvent event) {
        render(event.partialTicks);
    }

    public List<HudElement> getElements() {
        return elements;
    }

}