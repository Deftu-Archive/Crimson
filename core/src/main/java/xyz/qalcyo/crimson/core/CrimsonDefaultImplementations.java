/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core;

import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.qalcyo.eventbus.QalcyoEventBus;
import xyz.qalcyo.crimson.core.integration.hypixel.HypixelHelper;
import xyz.qalcyo.crimson.core.util.*;

/**
 * Holds default implmenetations of Crimson classes.
 */
class CrimsonDefaultImplementations {

    static final Logger LOGGER = LogManager.getLogger("Crimson");
    static final CrimsonJavaArguments JAVA_ARGUMENTS = new CrimsonJavaArguments();
    static final QalcyoEventBus EVENT_BUS = new QalcyoEventBus();
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