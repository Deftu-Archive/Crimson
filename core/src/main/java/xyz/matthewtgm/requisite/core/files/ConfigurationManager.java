package xyz.matthewtgm.requisite.core.files;

import xyz.matthewtgm.tgmconfig.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager extends Thread {

    private final Configuration configuration;
    private final List<IConfigurable> configurables;

    public ConfigurationManager(Configuration configuration) {
        this.configuration = configuration;
        this.configurables = new ArrayList<>();
    }

    public void update() {
        for (IConfigurable configurable : configurables) {
            configurable.load(this);
            configurable.save(this);
        }
    }

    public ConfigurationManager addConfigurable(IConfigurable configurable) {
        this.configurables.add(configurable);
        configurable.load(this);
        configurable.save(this);

        return this;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<IConfigurable> getConfigurables() {
        return configurables;
    }

}