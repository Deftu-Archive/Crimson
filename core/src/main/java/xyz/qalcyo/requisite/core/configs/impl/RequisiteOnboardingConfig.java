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

package xyz.qalcyo.requisite.core.configs.impl;

import xyz.qalcyo.requisite.core.configs.IConfigChild;
import xyz.qalcyo.requisite.core.configs.IConfigObject;
import xyz.qalcyo.simpleconfig.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RequisiteOnboardingConfig implements IConfigObject {

    private final Configuration configuration;
    private final List<IConfigChild> children = new ArrayList<>();

    private boolean prompted;
    private boolean tos;

    public RequisiteOnboardingConfig(String name, File directory) {
        this.configuration = new Configuration(name, directory);

        if (!configuration.hasKey("prompted"))
            configuration.add("prompted", false).save();
        prompted = configuration.getAsBoolean("prompted");
        if (!configuration.hasKey("tos"))
            configuration.add("tos", false).save();
        this.tos = configuration.getAsBoolean("tos");
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<IConfigChild> getChildren() {
        return children;
    }

    public boolean isPrompted() {
        return prompted;
    }

    public void setPrompted(boolean prompted) {
        this.prompted = prompted;
        configuration.add("prompted", prompted).save();
    }

    public boolean isTos() {
        return tos;
    }

    public void setTos(boolean tos) {
        this.tos = tos;
        configuration.add("tos", tos).save();
    }

}