package xyz.deftu.requisite.core.discord;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;

public class DiscordCore {

    private final long applicationId;

    private boolean initialized;
    private DiscordNativeLoader nativeLoader;
    private Core core;
    private boolean running;

    public DiscordCore(long applicationId) {
        this.applicationId = applicationId;
        initialize();
    }

    public void initialize() {
        if (initialized)
            return;

        nativeLoader = new DiscordNativeLoader();
        nativeLoader.initialize();
        initializeCore();

        initialized = true;
    }

    private void initializeCore() {
        CreateParams createParams = new CreateParams();
        createParams.setClientID(applicationId);
        createParams.setFlags(CreateParams.Flags.NO_REQUIRE_DISCORD);

        try {
            core = new Core(createParams);
            running = true;
            new Thread(() -> {
                while (running) {
                    core.runCallbacks();

                    try {
                        Thread.sleep(16);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Thread.currentThread().interrupt();
            }, "Requisite Discord Integration Callbacks Thread").start();
        } catch (Exception e) {
            e.printStackTrace();
            running = false;
        }
    }

    public Core getCore() {
        return core;
    }

}