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

package xyz.qalcyo.requisite.core.files

import xyz.qalcyo.requisite.core.IRequisite
import java.io.File

class FileManager(val requisite: IRequisite) {

    fun getConfigDirectory(gameDirectory: File?): File =
        checkExistence(File(gameDirectory, "config"))
    fun getQalcyoDirectory(configDirectory: File?): File =
        checkExistence(File(configDirectory, "Qalcyo"))
    fun getRequisiteDirectory(qalcyoDirectory: File?): File =
        checkExistence(File(qalcyoDirectory, requisite.name))

    private fun checkExistence(directory: File): File {
        check(!(!directory.exists() && !directory.mkdirs())) { "Unable to create Requisite directories." }
        return directory
    }

}