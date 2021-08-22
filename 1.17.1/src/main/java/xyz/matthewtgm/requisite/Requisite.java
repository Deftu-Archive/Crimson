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

import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.events.TickEvent;
import xyz.matthewtgm.requisite.core.integration.ModMetadata;
import xyz.matthewtgm.simpleeventbus.EventSubscriber;

import java.io.File;

public class Requisite implements IRequisite {

    private static final Requisite instance = new Requisite();
    private RequisiteManager manager;

    public void initialize(File gameDir) {
        if (manager == null)
            manager = new RequisiteManager();

        manager.initialize(this, gameDir);
        manager.getEventBus().register(this);
        postInitialize();
    }

    public RequisiteManager getManager() {
        return manager;
    }

    public ModMetadata metadata() {
        return new ModMetadata("@NAME@", "@VER@");
    }

    public static Requisite getInstance() {
        return instance;
    }

}