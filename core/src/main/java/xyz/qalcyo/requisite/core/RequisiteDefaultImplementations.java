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
    static final Multithreading multithreading = new Multithreading();
    static final ReflectionHelper reflectionHelper = new ReflectionHelper();
    static final RomanNumeral romanNumerals = new RomanNumeral();
    static final StringHelper stringHelper = new StringHelper();
    static final MojangAPI mojangApi = new MojangAPI();

}