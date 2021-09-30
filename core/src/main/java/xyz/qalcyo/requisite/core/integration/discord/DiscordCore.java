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

package xyz.qalcyo.requisite.core.integration.discord;

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