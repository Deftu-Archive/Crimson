package xyz.matthewtgm.requisite.core.files;

public interface IConfigurable {
    void save(ConfigurationManager configurationManager);
    void load(ConfigurationManager configurationManager);
}