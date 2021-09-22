/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.core.commands;

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