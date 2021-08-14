package xyz.matthewtgm.requisite.core.keybinds;

import xyz.matthewtgm.json.entities.JsonElement;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.IConfigurable;
import xyz.matthewtgm.tgmconfig.Configuration;
import xyz.matthewtgm.tgmconfig.Subconfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KeyBindRegistry implements IConfigurable {

    private final IRequisite requisite;

    private final List<KeyBind> keyBinds = new ArrayList<>();
    private final List<String> categories = new ArrayList<>();

    public KeyBindRegistry(IRequisite requisite) {
        this.requisite = requisite;
    }

    public void register(KeyBind keyBind) {
        keyBinds.add(keyBind);
        if (!categories.contains(keyBind.getCategory())) {
            categories.add(keyBind.getCategory());
        }

        load(keyBind);
        save(keyBind);
    }

    public void unregister(String name) {
        Optional<KeyBind> keyBindOptional = keyBinds.stream().filter(keyBind -> keyBind.getName().equals(name)).findFirst();
        if (keyBindOptional.isPresent()) {
            KeyBind keyBind = keyBindOptional.get();
            String category = keyBind.getCategory();
            keyBinds.remove(keyBind);

            boolean categoryStillPresent = false;
            for (KeyBind bind : keyBinds) {
                if (bind.getCategory().equals(category)) {
                    categoryStillPresent = true;
                    break;
                }
            }
            if (categoryStillPresent) {
                categories.remove(category);
            }
        }
    }

    public void unregister(KeyBind keyBind) {
        unregister(keyBind.getName());
    }

    public void save(ConfigurationManager configurationManager) {
        for (KeyBind keyBind : keyBinds) {
            save(keyBind);
        }
    }

    public void save(KeyBind keyBind) {
        Configuration configuration = requisite.getManager().getConfigurationManager().getConfiguration();
        if (!configuration.hasKey("keybinds"))
            configuration.createSubconfiguration("keybinds");
        Subconfiguration keyBindsConfiguration = configuration.getSubconfiguration("keybinds");

        if (!keyBindsConfiguration.hasKey(keyBind.getCategory()))
            keyBindsConfiguration.createSubconfiguration(keyBind.getCategory());
        Subconfiguration categoryConfiguration = keyBindsConfiguration.getSubconfiguration(keyBind.getCategory());

        categoryConfiguration.add(keyBind.getName(), keyBind.getKey());
    }

    public void load(ConfigurationManager configurationManager) {
        for (KeyBind keyBind : keyBinds) {
            load(keyBind);
        }
    }

    public void load(KeyBind keyBind) {
        Configuration configuration = requisite.getManager().getConfigurationManager().getConfiguration();
        if (!configuration.hasKey("keybinds"))
            configuration.createSubconfiguration("keybinds");
        Subconfiguration keyBindsConfiguration = configuration.getSubconfiguration("keybinds");

        if (!keyBindsConfiguration.hasKey(keyBind.getCategory()))
            keyBindsConfiguration.createSubconfiguration(keyBind.getCategory());
        Subconfiguration categoryConfiguration = keyBindsConfiguration.getSubconfiguration(keyBind.getCategory());

        if (categoryConfiguration.hasKey(keyBind.getName())) {
            JsonElement keyBindElement = categoryConfiguration.get(keyBind.getName());
            keyBind.updateKey(keyBindElement.isDouble() ? (int) keyBindElement.getAsDouble() : keyBindElement.isFloat() ? (int) keyBindElement.getAsFloat() : keyBindElement.getAsInt());
        }
    }

    public List<KeyBind> getKeyBinds() {
        return keyBinds;
    }

}