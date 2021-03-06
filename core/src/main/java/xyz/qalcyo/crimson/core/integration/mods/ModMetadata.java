/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core.integration.mods;

public class ModMetadata {

    private final String name, version;
    private Class<? extends IModConfigurationMenu> configurationMenu;
    private String command;

    ModMetadata(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Class<? extends IModConfigurationMenu> getConfigurationMenu() {
        return configurationMenu;
    }

    public String getCommand() {
        return command;
    }

    public ModMetadata setCommand(String command) {
        if (!command.startsWith("/"))
            command = "/" + command;
        this.command = command;
        return this;
    }

    public ModMetadata setConfigurationMenu(Class<? extends IModConfigurationMenu> configurationMenu) {
        this.configurationMenu = configurationMenu;
        return this;
    }

    public static ModMetadata from(String name, String version) {
        return new ModMetadata(name, version);
    }

}