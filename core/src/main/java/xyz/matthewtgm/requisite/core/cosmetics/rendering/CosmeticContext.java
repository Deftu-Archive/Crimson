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

package xyz.matthewtgm.requisite.core.cosmetics.rendering;

public interface CosmeticContext {

    /* Global. */
    boolean isPlayerInvisible();
    boolean isPlayerWearingPart(String name);
    boolean doesPlayerHaveCape();
    boolean isPlayerSneaking();

    int getPlayerPosX();
    int getPlayerPosY();
    int getPlayerPosZ();

    int getPlayerPrevPosX();
    int getPlayerPrevPosY();
    int getPlayerPrevPosZ();

    int getPlayerRenderYawOffset();
    int getPlayerPrevRenderYawOffset();

    int getPlayerCameraYaw();
    int getPlayerPrevCameraYaw();

    float partialTicks();
    float scale();

    /* 1.12 and under. */
    int getPlayerChasingPosX();
    int getPlayerChasingPosY();
    int getPlayerChasingPosZ();

    int getPlayerPrevChasingPosX();
    int getPlayerPrevChasingPosY();
    int getPlayerPrevChasingPosZ();

    /* 1.13 and over. */

}