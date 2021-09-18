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

package xyz.qalcyo.requisite.core.hypixel

import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.hypixel.api.HypixelAPI
import xyz.qalcyo.requisite.core.hypixel.locraw.HypixelLocrawManager
import java.util.regex.Pattern

class HypixelHelper(
    private val requisite: IRequisite
) {

    val locrawManager: HypixelLocrawManager = HypixelLocrawManager(requisite, this)
    val api = HypixelAPI()

    val isOnHypixel: Boolean
        get() {
            val HYPIXEL_SERVER_BRAND = "Hypixel BungeeCord"
            return if (!requisite.serverHelper.isSingleplayer && requisite.playerHelper.isPlayerPresent && requisite.serverHelper.brand != null) {
                val matcher = SERVER_BRAND_PATTERN.matcher(requisite.serverHelper.brand)
                if (matcher.find()) {
                    matcher.group(1).startsWith(HYPIXEL_SERVER_BRAND)
                } else {
                    false
                }
            } else {
                false
            }
        }

    companion object {
        private val SERVER_BRAND_PATTERN = Pattern.compile("(.+) <- (?:.+)")
    }

}