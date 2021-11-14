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

package xyz.qalcyo.requisite;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.input.Keyboard;
import xyz.qalcyo.mango.Multithreading;
import xyz.qalcyo.requisite.bridge.Bridge;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.RequisiteEventManager;
import xyz.qalcyo.requisite.core.RequisiteInfo;
import xyz.qalcyo.requisite.core.commands.CommandRegistry;
import xyz.qalcyo.requisite.core.configs.ConfigManager;
import xyz.qalcyo.requisite.core.events.initialization.InitializationEvent;
import xyz.qalcyo.requisite.core.files.FileManager;
import xyz.qalcyo.requisite.core.keybinds.KeyBindRegistry;
import xyz.qalcyo.requisite.core.keybinds.KeyBinds;
import xyz.qalcyo.requisite.core.networking.RequisiteClientSocket;
import xyz.qalcyo.requisite.cosmetics.CosmeticManager;
import xyz.qalcyo.requisite.gui.factory.ComponentFactory;
import xyz.qalcyo.requisite.gui.screens.ChangelogMenu;
import xyz.qalcyo.requisite.gui.screens.CreditsMenu;
import xyz.qalcyo.requisite.gui.screens.TestMenu;
import xyz.qalcyo.requisite.gui.screens.main.RequisiteMenu;
import xyz.qalcyo.requisite.integration.mods.ModIntegration;
import xyz.qalcyo.requisite.networking.packets.cosmetics.CosmeticRetrievePacket;
import xyz.qalcyo.requisite.notifications.Notifications;
import xyz.qalcyo.requisite.rendering.EnhancedFontRenderer;
import xyz.qalcyo.requisite.util.*;

/**
 * The main class for Requisite's storage and initialization process.
 */
@Mod(
        name = RequisiteInfo.NAME,
        version = RequisiteInfo.VER,
        modid = RequisiteInfo.ID,
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Requisite implements RequisiteAPI {

    /* Constants. */
    private static final Requisite INSTANCE = new Requisite();
    private static boolean initialized;

    /* Services. */
    private FileManager fileManager;
    private ConfigManager configManager;
    private Notifications notifications;
    private RequisiteClientSocket requisiteSocket;
    private ModIntegration modIntegration;
    private CommandRegistry commandRegistry;
    private KeyBindRegistry keyBindRegistry;
    private ComponentFactory componentFactory;
    private Bridge bridge;
    private RequisiteEventManager internalEventManager;
    private RequisiteEventListener internalEventListener;

    private CosmeticManager cosmeticManager;

    /* Utilities. */
    private EnhancedFontRenderer enhancedFontRenderer;
    private GuiHelper guiHelper;
    private ChatHelper chatHelper;
    private SupportHelper supportHelper;
    private MouseHelper mouseHelper;
    private PositionHelper positionHelper;
    private RenderHelper renderHelper;
    private MessageQueue messageQueue;
    private ServerHelper serverHelper;
    private GlHelper glHelper;
    private ResourceHelper resourceHelper;
    private KeyboardHelper keyboardHelper;

    public void initialize(InitializationEvent event) {
        if (initialized)
            return;

        /* Initialize services. */
        fileManager = new FileManager(this);
        configManager = new ConfigManager(fileManager.getRequisiteConfigDirectory(fileManager.getRequisiteDirectory(fileManager.getQalcyoDirectory(Launch.minecraftHome))));
        notifications = new Notifications(this);
        requisiteSocket = new RequisiteClientSocket(this);
        if (configManager.getOnboarding().isTos())
            requisiteSocket.awaitConnect();
        requisiteSocket.register("COSMETIC_RETRIEVE", CosmeticRetrievePacket.class);
        modIntegration = new ModIntegration();
        commandRegistry = new CommandRegistry();
        keyBindRegistry = new KeyBindRegistry(this);
        componentFactory = new ComponentFactory();
        (bridge = new Bridge()).start();
        internalEventManager = new RequisiteEventManager();
        internalEventListener = new RequisiteEventListener(this);

        cosmeticManager = new CosmeticManager();

        /* Initialize utilities. */
        Multithreading.runAsync(() -> {
            enhancedFontRenderer = new EnhancedFontRenderer();
            guiHelper = new GuiHelper();
            chatHelper = new ChatHelper();
            supportHelper = new SupportHelper();
            mouseHelper = new MouseHelper();
            positionHelper = new PositionHelper();
            renderHelper = new RenderHelper();
            messageQueue = new MessageQueue(this);
            serverHelper = new ServerHelper();
            glHelper = new GlHelper();
            resourceHelper = new ResourceHelper();
            keyboardHelper = new KeyboardHelper();
            cosmeticManager.start();
        });

        getKeyBindRegistry().register(KeyBinds.from("Open menu", "Requisite", Keyboard.KEY_HOME, () -> guiHelper.open(new RequisiteMenu())));

        getMetadata().setConfigurationMenu(RequisiteMenu.class);
        initialized = true;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public RequisiteClientSocket getRequisiteSocket() {
        return requisiteSocket;
    }

    public ModIntegration getModIntegration() {
        return modIntegration;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public KeyBindRegistry getKeyBindRegistry() {
        return keyBindRegistry;
    }

    public ComponentFactory getComponentFactory() {
        return componentFactory;
    }

    public Bridge getBridge() {
        return bridge;
    }

    public RequisiteEventManager getInternalEventManager() {
        return internalEventManager;
    }

    public RequisiteEventListener getInternalEventListener() {
        return internalEventListener;
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }

    public void openRequisiteMenu(int pageIndex) {
        guiHelper.open(new RequisiteMenu(pageIndex));
    }

    public void openCreditsMenu() {
        guiHelper.open(new CreditsMenu());
    }

    public void openChangelogMenu() {
        guiHelper.open(new ChangelogMenu());
    }

    public EnhancedFontRenderer getEnhancedFontRenderer() {
        return enhancedFontRenderer;
    }

    public GuiHelper getGuiHelper() {
        return guiHelper;
    }

    public ChatHelper getChatHelper() {
        return chatHelper;
    }

    public SupportHelper getSupportHelper() {
        return supportHelper;
    }

    public MouseHelper getMouseHelper() {
        return mouseHelper;
    }

    public PositionHelper getPositionHelper() {
        return positionHelper;
    }

    public RenderHelper getRenderHelper() {
        return renderHelper;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    public ServerHelper getServerHelper() {
        return serverHelper;
    }

    public GlHelper getGlHelper() {
        return glHelper;
    }

    public ResourceHelper getResourceHelper() {
        return resourceHelper;
    }

    public KeyboardHelper getKeyboardHelper() {
        return keyboardHelper;
    }

    /**
     * @return An instance of {@link Requisite}.
     */
    public static Requisite getInstance() {
        return INSTANCE;
    }

    public static boolean isInitialized() {
        return initialized;
    }

}