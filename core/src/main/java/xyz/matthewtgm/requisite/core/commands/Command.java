/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.matthewtgm.requisite.core.commands;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Command {

    private final String name;
    private final Runnable executor;
    private final List<String> aliases;

    private final Map<String, Runnable> subCommands;

    public Command(String name, Runnable executor, List<String> aliases) {
        this.name = name;
        this.executor = executor;
        this.aliases = Collections.unmodifiableList(aliases);

        this.subCommands = Maps.newHashMap();
    }

    public Command(String name, Runnable executor) {
        this(name, executor, Lists.newArrayList());
    }

    public String getName() {
        return name;
    }

    public Runnable getExecutor() {
        return executor;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public Map<String, Runnable> getSubCommands() {
        return subCommands;
    }

    public Runnable getSubCommand(String name) {
        return subCommands.get(name);
    }

    public void addSubCommand(String name, Runnable executor) {
        subCommands.put(name, executor);
    }

    public void removeSubCommand(String name) {
        subCommands.remove(name);
    }

}