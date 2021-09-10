package xyz.deftu.requisite.core.commands;

import java.util.Collections;
import java.util.List;

public class CommandData {

    private final String name;
    private final List<String> aliases;
    private List<String> tabCompletions;

    public CommandData(String name, List<String> aliases, List<String> tabCompletions) {
        this.name = name;
        this.aliases = aliases;
        this.tabCompletions = tabCompletions;
    }

    public boolean has(String input) {
        return name.equalsIgnoreCase(input) || aliases.stream().anyMatch(alias -> alias.equalsIgnoreCase(input));
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(aliases);
    }

    public void addTabCompletion(String tabCompletion) {
        tabCompletions.add(tabCompletion);
    }

    public List<String> getTabCompletions() {
        return tabCompletions;
    }

    public void setTabCompletions(List<String> tabCompletions) {
        this.tabCompletions = tabCompletions;
    }

    public static CommandData from(String name, List<String> aliases, List<String> tabCompletions) {
        return new CommandData(name, aliases, tabCompletions);
    }

    public static CommandData from(String name) {
        return from(name, Collections.emptyList(), Collections.emptyList());
    }

}