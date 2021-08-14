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

package xyz.matthewtgm.requisite.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EnumPlayerModelParts;
import xyz.matthewtgm.requisite.core.cosmetics.rendering.CosmeticContext;

public class CosmeticContextWrapper implements CosmeticContext {

    private final AbstractClientPlayer clientPlayer;
    private final float partialTicks;
    private final float scale;

    public CosmeticContextWrapper(AbstractClientPlayer clientPlayer, float partialTicks, float scale) {
        this.clientPlayer = clientPlayer;
        this.partialTicks = partialTicks;
        this.scale = scale;
    }

    public void colour(float red, float green, float blue, float alpha) {
        GlStateManager.color(red, green, blue, alpha);
    }

    public void bind() {

    }

    public void push() {
        GlStateManager.pushMatrix();
    }

    public void translate(float x, float y, float z) {
        GlStateManager.translate(x, y, z);
    }

    public void rotate(float angle, float x, float y, float z) {
        GlStateManager.rotate(angle, x, y, z);
    }

    public void pop() {
        GlStateManager.popMatrix();
    }

    public boolean isPlayerInvisible() {
        return clientPlayer.isInvisible();
    }

    public boolean isPlayerWearingPart(String name) {
        return clientPlayer.isWearing(EnumPlayerModelParts.valueOf(name.toUpperCase()));
    }

    public boolean doesPlayerHaveCape() {
        return clientPlayer.getLocationCape() != null;
    }

    public boolean isPlayerSneaking() {
        return clientPlayer.isSneaking();
    }

    public boolean isPlayerOnGround() {
        return clientPlayer.onGround;
    }

    public double getPlayerMotionX() {
        return clientPlayer.motionX;
    }

    public double getPlayerMotionY() {
        return clientPlayer.motionY;
    }

    public double getPlayerMotionZ() {
        return clientPlayer.motionZ;
    }

    public double getPlayerPosX() {
        return clientPlayer.posX;
    }

    public double getPlayerPosY() {
        return clientPlayer.posY;
    }

    public double getPlayerPosZ() {
        return clientPlayer.posZ;
    }

    public double getPlayerPrevPosX() {
        return clientPlayer.prevPosX;
    }

    public double getPlayerPrevPosY() {
        return clientPlayer.prevPosY;
    }

    public double getPlayerPrevPosZ() {
        return clientPlayer.prevPosZ;
    }

    public float getPlayerRenderYawOffset() {
        return clientPlayer.renderYawOffset;
    }

    public float getPlayerPrevRenderYawOffset() {
        return clientPlayer.prevRenderYawOffset;
    }

    public float getPlayerCameraYaw() {
        return clientPlayer.cameraYaw;
    }

    public float getPlayerPrevCameraYaw() {
        return clientPlayer.prevCameraYaw;
    }

    public float partialTicks() {
        return partialTicks;
    }

    public float scale() {
        return scale;
    }

    public double getPlayerChasingPosX() {
        return clientPlayer.chasingPosX;
    }

    public double getPlayerChasingPosY() {
        return clientPlayer.chasingPosY;
    }

    public double getPlayerChasingPosZ() {
        return clientPlayer.chasingPosZ;
    }

    public double getPlayerPrevChasingPosX() {
        return clientPlayer.prevChasingPosX;
    }

    public double getPlayerPrevChasingPosY() {
        return clientPlayer.prevChasingPosY;
    }

    public double getPlayerPrevChasingPosZ() {
        return clientPlayer.prevChasingPosZ;
    }

    public float getPlayerDistanceWalkedModified() {
        return clientPlayer.distanceWalkedModified;
    }

    public float getPlayerPrevDistanceWalkedModified() {
        return clientPlayer.prevDistanceWalkedModified;
    }

}