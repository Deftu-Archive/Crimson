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

package xyz.qalcyo.requisite.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.qalcyo.requisite.core.util.*;
import xyz.qalcyo.requisite.core.files.configs.PrivacyConfigurations;
import xyz.qalcyo.simpleeventbus.SimpleEventBus;

class RequisiteDefaultImplementations {

    static final Logger logger = LogManager.getLogger("Requisite");
    static final SimpleEventBus eventBus = new SimpleEventBus();

    static final PrivacyConfigurations privacyConfigurations = new PrivacyConfigurations();

    static final ColourHelper colourHelper = new ColourHelper();
    static final LoggingHelper loggingHelper = new LoggingHelper();
    static final ClipboardHelper clipboardHelper = new ClipboardHelper();
    static final DateHelper dateHelper = new DateHelper();
    static final EasingHelper easingHelper = new EasingHelper();
    static final MathHelper mathHelper = new MathHelper();
    static final ReflectionHelper reflectionHelper = new ReflectionHelper();
    static final RomanNumeral romanNumerals = new RomanNumeral();
    static final StringHelper stringHelper = new StringHelper();
    static final MojangAPI mojangApi = new MojangAPI();

}