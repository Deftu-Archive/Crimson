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

package xyz.qalcyo.requisite.core

import gg.essential.universal.ChatColor
import org.apache.logging.log4j.Logger
import xyz.qalcyo.json.util.JsonApiHelper
import xyz.qalcyo.requisite.core.commands.CommandRegistry
import xyz.qalcyo.requisite.core.cosmetics.CosmeticManager
import xyz.qalcyo.requisite.core.files.ConfigurationManager
import xyz.qalcyo.requisite.core.files.FileManager
import xyz.qalcyo.requisite.core.files.configs.PrivacyConfigurations
import xyz.qalcyo.requisite.core.hypixel.HypixelHelper
import xyz.qalcyo.requisite.core.integration.IMod
import xyz.qalcyo.requisite.core.integration.IModIntegration
import xyz.qalcyo.requisite.core.keybinds.KeyBindRegistry
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket
import xyz.qalcyo.requisite.core.notifications.INotifications
import xyz.qalcyo.requisite.core.rendering.IEnhancedFontRenderer
import xyz.qalcyo.requisite.core.util.*
import xyz.qalcyo.requisite.core.util.messages.IMessageQueue
import xyz.qalcyo.simpleeventbus.SimpleEventBus
import java.io.File
import java.net.URI
import java.util.*

interface IRequisite : IMod {

    val name: String
        get() = RequisiteInfo.NAME
    val version: String
        get() = RequisiteInfo.VER
    val id: String
        get() = RequisiteInfo.ID

    val chatPrefix: String
        get() = "${ChatColor.GRAY}[$name]${ChatColor.GRAY}]"

    val javaArguments: RequisiteJavaArguments
        get() = RequisiteDefaultImplementations.javaArguments
    val logger: Logger
        get() = RequisiteDefaultImplementations.logger
    val eventBus: SimpleEventBus
        get() = RequisiteDefaultImplementations.eventBus
    val fileManager: FileManager
    val configurationManager: ConfigurationManager
    val notifications: INotifications
    val multithreading: Multithreading
        get() = RequisiteDefaultImplementations.multithreading
    val requisiteSocket: RequisiteClientSocket
    val cosmeticManager: CosmeticManager<*>
    val modIntegration: IModIntegration
    val commandRegistry: CommandRegistry
    val keyBindRegistry: KeyBindRegistry
    val internalEventManager: RequisiteEventManager
    val internalEventListener: IEventListener

    val privacyConfigurations: PrivacyConfigurations
        get() = RequisiteDefaultImplementations.privacyConfigurations

    val enhancedFontRenderer: IEnhancedFontRenderer
    val playerHelper: IPlayerHelper
    val chatHelper: IChatHelper
    val colourHelper: ColourHelper
        get() = RequisiteDefaultImplementations.colourHelper
    val loggingHelper: LoggingHelper
        get() = RequisiteDefaultImplementations.loggingHelper
    val clipboardHelper: ClipboardHelper
        get() = RequisiteDefaultImplementations.clipboardHelper
    val dateHelper: DateHelper
        get() = RequisiteDefaultImplementations.dateHelper
    val easingHelper: EasingHelper
        get() = RequisiteDefaultImplementations.easingHelper
    val mathHelper: MathHelper
        get() = RequisiteDefaultImplementations.mathHelper
    val mouseHelper: IMouseHelper
    val positionHelper: IPositionHelper
    val romanNumerals: RomanNumeral
        get() = RequisiteDefaultImplementations.romanNumerals
    val hypixelHelper: HypixelHelper
    val renderHelper: IRenderHelper
    val stringHelper: StringHelper
        get() = RequisiteDefaultImplementations.stringHelper
    val messageQueue: IMessageQueue
    val serverHelper: IServerHelper
    val mojangApi: MojangAPI
        get() = RequisiteDefaultImplementations.mojangApi

    fun initialize(gameDir: File?): Boolean
    fun finish(gameDir: File?): Boolean {
        val initial = initialize(gameDir)
        configurationManager.addConfigurable(privacyConfigurations)
        return initial
    }

    fun openMenu()

    /* Default. */
    fun fetchSocketUri(): URI {
        if (javaArguments.websocketDebug)
            return URI.create("ws://localhost:8080/")
        val `object` = JsonApiHelper.getJsonObject("https://raw.githubusercontent.com/TGMDevelopment/RequisiteData/main/websocket.json")
        var encoded = `object`.getAsString("uri")
        for (i in 0 until `object`.getAsInt("loop")) encoded = String(Base64.getDecoder().decode(encoded))
        return URI.create(encoded)
    }

}