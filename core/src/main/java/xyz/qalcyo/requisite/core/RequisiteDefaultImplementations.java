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

import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.qalcyo.eventbus.QalcyoEventBus;
import xyz.qalcyo.requisite.core.integration.hypixel.HypixelHelper;
import xyz.qalcyo.requisite.core.localization.ModLocalizationFactory;
import xyz.qalcyo.requisite.core.util.*;

/**
 * Holds default implmenetations of Requisite classes.
 */
class RequisiteDefaultImplementations {

    static final Logger LOGGER = LogManager.getLogger("Requisite");
    static final RequisiteJavaArguments JAVA_ARGUMENTS = new RequisiteJavaArguments();
    static final QalcyoEventBus EVENT_BUS = new QalcyoEventBus();
    static final ModLocalizationFactory MOD_LOCALIZATION_FACTORY = new ModLocalizationFactory();
    static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    static final PastebinFactory PASTEBIN_FACTORY = new PastebinFactory();

    static final ModHelper MOD_HELPER = new ModHelper();
    static final ColourHelper COLOUR_HELPER = new ColourHelper();
    static final LoggingHelper LOGGING_HELPER = new LoggingHelper();
    static final UniversalLogger UNIVERSAL_LOGGER = new UniversalLogger();
    static final ClipboardHelper CLIPBOARD_HELPER = new ClipboardHelper();
    static final DateHelper DATE_HELPER = new DateHelper();
    static final EasingHelper EASING_HELPER = new EasingHelper();
    static final MathHelper MATH_HELPER = new MathHelper();
    static final ReflectionHelper REFLECTION_HELPER = new ReflectionHelper();
    static final RomanNumeral ROMAN_NUMERAL = new RomanNumeral();
    static final HypixelHelper HYPIXEL_HELPER = new HypixelHelper();
    static final StringHelper STRING_HELPER = new StringHelper();

}