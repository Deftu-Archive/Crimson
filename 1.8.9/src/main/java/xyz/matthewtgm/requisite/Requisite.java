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

import net.minecraftforge.fml.common.Mod;
import org.lwjgl.input.Keyboard;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.data.IScreenPosition;
import xyz.matthewtgm.requisite.core.hud.HudElement;
import xyz.matthewtgm.requisite.core.integration.ModMetadata;
import xyz.matthewtgm.requisite.core.keybinds.KeyBind;

import java.io.File;

@Mod(
        name = "@NAME@",
        version = "@VER@",
        modid = "@ID@",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Requisite implements IRequisite {

    private static final Requisite instance = new Requisite();
    private RequisiteManager manager;

    public void initialize(File gameDirectory) {
        if (manager == null)
            manager = new RequisiteManager();

        manager.initialize(this, gameDirectory);
        postInitialize();

        manager.getKeyBindRegistry().register(new KeyBind("Requisite", "Requisite", Keyboard.KEY_N) {
            public void press() {
                manager.getLogger().warn("press");
            }
            public void hold() {
                manager.getLogger().warn("hold");
            }
            public void release() {
                manager.getLogger().warn("release");
            }
        });

        manager.openMenu();
    }

    public RequisiteManager getManager() {
        return manager;
    }

    public static Requisite getInstance() {
        return instance;
    }

    public ModMetadata metadata() {
        return new ModMetadata("@NAME@", "@VER@");
    }

}