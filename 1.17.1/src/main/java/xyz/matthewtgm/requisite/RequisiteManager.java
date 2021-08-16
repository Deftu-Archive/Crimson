/*
 * Requisite - Minecraft library mod
 * Copyright (C) 2021 MatthewTGM
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

package xyz.matthewtgm.requisite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.IRequisiteManager;
import xyz.matthewtgm.requisite.core.files.ConfigurationManager;
import xyz.matthewtgm.requisite.core.files.FileManager;
import xyz.matthewtgm.requisite.core.integration.IModIntegration;
import xyz.matthewtgm.requisite.core.keybinds.KeyBindRegistry;
import xyz.matthewtgm.requisite.core.networking.RequisiteClientSocket;
import xyz.matthewtgm.requisite.core.notifications.INotifications;
import xyz.matthewtgm.requisite.core.rendering.IEnhancedFontRenderer;
import xyz.matthewtgm.requisite.core.util.*;
import xyz.matthewtgm.requisite.core.util.messages.IMessageQueue;
import xyz.matthewtgm.requisite.integration.ModIntegration;
import xyz.matthewtgm.simpleeventbus.SimpleEventBus;
import xyz.matthewtgm.tgmconfig.Configuration;

import java.io.File;

public class RequisiteManager implements IRequisiteManager {

    private Logger logger;
    private SimpleEventBus eventBus;
    private FileManager fileManager;
    private ConfigurationManager configurationManager;
    private ModIntegration modIntegration;
    private RequisiteClientSocket socket;

    private KeyBindRegistry keyBindRegistry;

    public void initialize(IRequisite requisite, File gameDirectory) {
        logger = LogManager.getLogger("Requisite");
        eventBus = new SimpleEventBus();
        fileManager = new FileManager();
        configurationManager = new ConfigurationManager(new Configuration(fileManager.getRequisiteDirectory(fileManager.getTgmDevelopmentDirectory(fileManager.getConfigDirectory(gameDirectory)))));
        modIntegration = new ModIntegration(requisite);
        socket = new RequisiteClientSocket(fetchSocketUri(), requisite);

        keyBindRegistry = new KeyBindRegistry(requisite);
    }

    public Logger getLogger() {
        return logger;
    }

    public SimpleEventBus getEventBus() {
        return eventBus;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public IModIntegration getModIntegration() {
        return modIntegration;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return socket;
    }

    public void openMenu() {
        logger.info("LOL");
    }

    public KeyBindRegistry getKeyBindRegistry() {
        return keyBindRegistry;
    }

    public IEnhancedFontRenderer getEnhancedFontRenderer() {
        return null;
    }

    public IChatHelper getChatHelper() {
        return null;
    }

    public ColourHelper getColourHelper() {
        return null;
    }

    public LoggingHelper getLoggingHelper() {
        return null;
    }

    public UniversalLogger getUniversalLogger() {
        return null;
    }

    public ClipboardHelper getClipboardHelper() {
        return null;
    }

    public DateHelper getDateHelper() {
        return null;
    }

    public EasingHelper getEasingHelper() {
        return null;
    }

    public MathHelper getMathHelper() {
        return null;
    }

    public IMouseHelper getMouseHelper() {
        return null;
    }

    public Multithreading getMultithreading() {
        return null;
    }

    public INotifications getNotifications() {
        return null;
    }

    public ObjectHelper getObjectHelper() {
        return null;
    }

    public ReflectionHelper getReflectionHelper() {
        return null;
    }

    public RomanNumeral getRomanNumerals() {
        return null;
    }

    public IRenderHelper getRenderHelper() {
        return null;
    }

    public IStringHelper getStringHelper() {
        return null;
    }

    public IMessageQueue getMessageQueue() {
        return null;
    }

    public IServerHelper getServerHelper() {
        return null;
    }

    public MojangAPI getMojangApi() {
        return null;
    }

}