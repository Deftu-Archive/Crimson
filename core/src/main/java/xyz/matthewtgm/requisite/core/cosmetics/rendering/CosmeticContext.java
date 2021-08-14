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

    /* Methods. */
    void colour(float red, float green, float blue, float alpha);
    void bind();

    void push();
    void translate(float x, float y, float z);
    void rotate(float angle, float x, float y, float z);
    void pop();

    /* Global data. */
    boolean isPlayerInvisible();
    boolean isPlayerWearingPart(String name);
    boolean doesPlayerHaveCape();
    boolean isPlayerSneaking();
    boolean isPlayerOnGround();

    double getPlayerMotionX();
    double getPlayerMotionY();
    double getPlayerMotionZ();

    double getPlayerPosX();
    double getPlayerPosY();
    double getPlayerPosZ();

    double getPlayerPrevPosX();
    double getPlayerPrevPosY();
    double getPlayerPrevPosZ();

    float getPlayerRenderYawOffset();
    float getPlayerPrevRenderYawOffset();

    float getPlayerCameraYaw();
    float getPlayerPrevCameraYaw();

    float partialTicks();
    float scale();

    /* 1.12 and under data. */
    double getPlayerChasingPosX();
    double getPlayerChasingPosY();
    double getPlayerChasingPosZ();

    double getPlayerPrevChasingPosX();
    double getPlayerPrevChasingPosY();
    double getPlayerPrevChasingPosZ();

    float getPlayerDistanceWalkedModified();
    float getPlayerPrevDistanceWalkedModified();

    /* 1.13 and over data. */

}