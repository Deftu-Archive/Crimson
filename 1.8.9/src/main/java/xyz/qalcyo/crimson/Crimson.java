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

package xyz.qalcyo.crimson;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.input.Keyboard;
import xyz.qalcyo.crimson.core.CrimsonInfo;
import xyz.qalcyo.crimson.core.InternalEventManager;
import xyz.qalcyo.crimson.core.gui.screens.main.CrimsonMenuPage;
import xyz.qalcyo.crimson.core.networking.CrimsonClientSocket;
import xyz.qalcyo.mango.Multithreading;
import xyz.qalcyo.crimson.bridge.Bridge;
import xyz.qalcyo.crimson.core.CrimsonAPI;
import xyz.qalcyo.crimson.core.commands.CommandRegistry;
import xyz.qalcyo.crimson.core.configs.ConfigManager;
import xyz.qalcyo.crimson.core.events.initialization.InitializationEvent;
import xyz.qalcyo.crimson.core.files.FileManager;
import xyz.qalcyo.crimson.core.keybinds.KeyBindRegistry;
import xyz.qalcyo.crimson.core.keybinds.KeyBinds;
import xyz.qalcyo.crimson.cosmetics.CosmeticManager;
import xyz.qalcyo.crimson.gui.factory.ComponentFactory;
import xyz.qalcyo.crimson.gui.screens.ChangelogMenu;
import xyz.qalcyo.crimson.gui.screens.CreditsMenu;
import xyz.qalcyo.crimson.gui.screens.main.CrimsonMenu;
import xyz.qalcyo.crimson.integration.mods.ModIntegration;
import xyz.qalcyo.crimson.networking.packets.cosmetics.CosmeticRetrievePacket;
import xyz.qalcyo.crimson.notifications.Notifications;
import xyz.qalcyo.crimson.rendering.EnhancedFontRenderer;
import xyz.qalcyo.crimson.util.*;

/**
 * The main class for Crimson's storage and initialization process.
 */
@Mod(
        name = CrimsonInfo.NAME,
        version = CrimsonInfo.VER,
        modid = CrimsonInfo.ID,
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Crimson implements CrimsonAPI {

    /* Constants. */
    private static final Crimson INSTANCE = new Crimson();
    private static boolean initialized;

    /* Services. */
    private FileManager fileManager;
    private ConfigManager configManager;
    private Notifications notifications;
    private CrimsonClientSocket crimsonSocket;
    private ModIntegration modIntegration;
    private CommandRegistry commandRegistry;
    private KeyBindRegistry keyBindRegistry;
    private ComponentFactory componentFactory;
    private Bridge bridge;
    private InternalEventManager internalEventManager;
    private InternalEventListener internalEventListener;

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
        configManager = new ConfigManager(fileManager.getCrimsonConfigDirectory(fileManager.getCrimsonDirectory(fileManager.getQalcyoDirectory(Launch.minecraftHome))));
        notifications = new Notifications(this);
        crimsonSocket = new CrimsonClientSocket(this);
        if (configManager.getOnboarding().isTos())
            crimsonSocket.awaitConnect();
        crimsonSocket.register("COSMETIC_RETRIEVE", CosmeticRetrievePacket.class);
        modIntegration = new ModIntegration();
        commandRegistry = new CommandRegistry();
        keyBindRegistry = new KeyBindRegistry(this);
        componentFactory = new ComponentFactory();
        (bridge = new Bridge()).start();
        internalEventManager = new InternalEventManager();
        internalEventListener = new InternalEventListener(this);

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
            messageQueue = new MessageQueue();
            serverHelper = new ServerHelper();
            glHelper = new GlHelper();
            resourceHelper = new ResourceHelper();
            keyboardHelper = new KeyboardHelper();
            cosmeticManager.start();
        });

        getKeyBindRegistry().register(KeyBinds.from("Open menu", "Crimson", Keyboard.KEY_HOME, (Runnable) this::openCrimsonMenu));

        getMetadata().setConfigurationMenu(CrimsonMenu.class);
        initialized = true;
    }

    public void onOnboardingAccepted() {
        crimsonSocket.awaitConnect();
        guiHelper.open(null);
    }

    public void onOnboardingDenied() {
        guiHelper.open(null);
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

    public CrimsonClientSocket getCrimsonSocket() {
        return crimsonSocket;
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

    public InternalEventManager getInternalEventManager() {
        return internalEventManager;
    }

    public InternalEventListener getInternalEventListener() {
        return internalEventListener;
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }

    public void openCrimsonMenu(Class<? extends CrimsonMenuPage> pageClass) {
        guiHelper.open(new CrimsonMenu(pageClass));
    }

    public void openCrimsonMenu(int pageIndex) {
        guiHelper.open(new CrimsonMenu(pageIndex));
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
     * @return An instance of {@link Crimson}.
     */
    public static Crimson getInstance() {
        return INSTANCE;
    }

    public static boolean isInitialized() {
        return initialized;
    }

}