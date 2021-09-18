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
package xyz.qalcyo.requisite.core.files.configs

import xyz.qalcyo.requisite.core.files.ConfigurationManager
import xyz.qalcyo.requisite.core.files.IConfigurable
import xyz.qalcyo.simpleconfig.Configuration
import xyz.qalcyo.simpleconfig.Subconfiguration

class PrivacyConfigurations : IConfigurable {

    private var configuration: Configuration? = null
    private var subconfiguration: Subconfiguration? = null
    private var acceptedTerms = false

    override fun initialize(configuration: Configuration) {
        this.configuration = configuration
        if (!configuration.hasKey("privacy")) configuration.createSubconfiguration("privacy").save()
        subconfiguration = configuration.getSubconfiguration("privacy")
    }

    override fun save(configurationManager: ConfigurationManager) {
        val subconfiguration = configuration()
        if (!subconfiguration!!.hasKey("accepted_tos")) {
            subconfiguration.add("accepted_tos", false).parent.asConfiguration.save()
        }
    }

    override fun load(configurationManager: ConfigurationManager) {
        val subconfiguration = configuration()
        acceptedTerms = subconfiguration!!.hasKey("accepted_tos") && subconfiguration.getAsBoolean("accepted_tos")
    }

    override fun configuration(): Subconfiguration? =
        subconfiguration

    fun hasAcceptedTerms(): Boolean =
        acceptedTerms

    fun setAcceptedTerms(acceptedTerms: Boolean) {
        this.acceptedTerms = acceptedTerms
        val subconfiguration = configuration()
        subconfiguration!!.add("accepted_tos", acceptedTerms).parent.asConfiguration.save()
    }

}