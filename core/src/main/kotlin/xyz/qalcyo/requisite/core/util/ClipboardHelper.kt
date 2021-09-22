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

package xyz.qalcyo.requisite.core.util

import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

object ClipboardHelper {

    /**
     * @return The string currently copied to the clipboard.
     */
    val clipboardString: String
        get() = try {
            val transferable = clipboard.getContents(null)
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) transferable.getTransferData(DataFlavor.stringFlavor) as String else ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    /**
     * @param input The string to set the clipboard's content to.
     * @return Whether the process was successful.
     */
    fun setClipboardString(input: String?): Boolean {
        return try {
            val selection = StringSelection(input)
            clipboard.setContents(selection, null)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * @return The system clipboard.
     */
    val clipboard: Clipboard
        get() = Toolkit.getDefaultToolkit().systemClipboard
}