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

package xyz.qalcyo.requisite.core.configs;

import xyz.qalcyo.simpleconfig.Configuration;

import java.util.List;

public interface IConfigObject {
    Configuration getConfiguration();
    List<IConfigChild> getChildren();
    default void addChild(IConfigChild child) {
        Configuration configuration = getConfiguration();
        String name = child.getName();
        if (!configuration.hasKey(name))
            configuration.createSubconfiguration(name).save();
        child.initialize(configuration, configuration.getSubconfiguration(name));
        getChildren().add(child);
    }
}