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

package xyz.qalcyo.requisite.core.files;

import xyz.qalcyo.requisite.core.IRequisite;

import java.io.File;

public class FileManager {

    private final IRequisite requisite;

    public FileManager(IRequisite requisite) {
        this.requisite = requisite;
    }

    public File getConfigDirectory(File gameDirectory) {
       return checkExistence(new File(gameDirectory, "config"));
    }

    public File getQalcyoDirectory(File configDirectory) {
        return checkExistence(new File(configDirectory, "Qalcyo"));
    }

    public File getRequisiteDirectory(File qalcyoDirectory) {
        return checkExistence(new File(qalcyoDirectory, requisite.name()));
    }

    private File checkExistence(File directory) {
        if (!directory.exists() && !directory.mkdirs())
            throw new IllegalStateException("Unable to create Requisite directories.");

        return directory;
    }

}