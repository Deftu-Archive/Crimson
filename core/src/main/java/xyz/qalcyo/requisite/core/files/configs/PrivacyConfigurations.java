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

package xyz.qalcyo.requisite.core.files.configs;

import xyz.qalcyo.requisite.core.files.ConfigurationManager;
import xyz.qalcyo.requisite.core.files.IConfigurable;
import xyz.qalcyo.simpleconfig.Configuration;
import xyz.qalcyo.simpleconfig.Subconfiguration;

public class PrivacyConfigurations implements IConfigurable {

    private Configuration configuration;
    private Subconfiguration subconfiguration;

    private boolean acceptedTerms;

    public void initialize(Configuration configuration) {
        this.configuration = configuration;
        if (!configuration.hasKey("privacy"))
            configuration.createSubconfiguration("privacy").save();
        this.subconfiguration = configuration.getSubconfiguration("privacy");
    }

    public void save(ConfigurationManager configurationManager) {
        Subconfiguration subconfiguration = configuration();

        if (!subconfiguration.hasKey("accepted_tos")) {
            subconfiguration.add("accepted_tos", false).getParent().getAsConfiguration().save();
        }
    }

    public void load(ConfigurationManager configurationManager) {
        Subconfiguration subconfiguration = configuration();
        acceptedTerms = subconfiguration.hasKey("accepted_tos") && subconfiguration.getAsBoolean("accepted_tos");
    }

    public Subconfiguration configuration() {
        return subconfiguration;
    }

    public boolean hasAcceptedTerms() {
        return acceptedTerms;
    }

    public void setAcceptedTerms(boolean acceptedTerms) {
        this.acceptedTerms = acceptedTerms;

        Subconfiguration subconfiguration = configuration();
        subconfiguration.add("accepted_tos", acceptedTerms).getParent().getAsConfiguration().save();
    }

}