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

package xyz.qalcyo.requisite.util;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import xyz.qalcyo.requisite.core.util.IRenderHelper;

import java.awt.*;

public class RenderHelper implements IRenderHelper {

    public void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, int colour) {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, colour);
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, colour);
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, colour);

        drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, colour);
        drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, colour);
        drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, colour);
        drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, colour);
    }

    public void drawHollowRoundedRect(int x, int y, int width, int height, int thickness, int colour) {
        int radius = 4;
        drawHollowArc(x, y, radius, -180, -90, thickness, colour);
        drawVerticalLine(x, y + 3, y + height - 4, thickness, colour);
        drawHorizontalLine(x + 3, x + width - 5, y, thickness, colour);
        drawHollowArc(x + width - 8, y, radius, -270, -180, thickness, colour);
        drawVerticalLine(x + width - 1, y + 3, y + height - 4, thickness, colour);
        drawHorizontalLine(x + 4, x + width - 5, y + height - 1, thickness, colour);
        drawHollowArc(x + width - 8, y + height - 8, radius, 0, 90, thickness, colour);
        drawHollowArc(x, y + height - 8, radius, -90, -0, thickness, colour);
    }

    public void drawArc(int x, int y, int radius, int start, int end, Color colour) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f((float) colour.getRed() / 255, (float) colour.getGreen() / 255, (float) colour.getBlue() / 255, (float) colour.getAlpha() / 255);
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        worldRenderer.begin(6, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x, y, 0).endVertex();
        for (int i = (int) (start / 360.0 * 100); i <= (int) (end / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            worldRenderer.pos(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius, 0).endVertex();
        }
        Tessellator.getInstance().draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public void drawArc(int x, int y, int radius, int start, int end, int colour) {
        drawArc(x, y, radius, start, end, new Color(colour));
    }

    public void drawHollowArc(int x, int y, int radius, int start, int end, int thickness, Color colour) {
        radius -= thickness / 2;
        x += thickness / 2;
        y += thickness / 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth((float) thickness);
        GL11.glColor4d(colour.getRed() / 255.0, colour.getGreen() / 255.0, colour.getBlue() / 255.0, colour.getAlpha() / 255.0);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (double i = start; i <= end; i += 1) GL11.glVertex2d(x + radius + Math.sin(Math.toRadians(i)) * radius, y + radius + Math.cos(Math.toRadians(i)) * radius);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(1);
    }

    public void drawHollowArc(int x, int y, int radius, int start, int end, int thickness, int colour) {
        drawHollowArc(x, y, radius, start, end, thickness, new Color(colour));
    }

    public void drawRect(int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            int j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void drawRectEnhanced(int x, int y, int width, int height, int color) {
        Gui.drawRect(x, y, width + x, height + y, color);
    }

    public void drawHollowRect(int x, int y, int width, int height, int thickness, int colour) {
        drawHorizontalLine(x, x + width, y, thickness, colour);
        drawHorizontalLine(x, x + width, y + height, thickness, colour);
        drawVerticalLine(x, y + height, y, thickness, colour);
        drawVerticalLine(x + width, y + height, y, thickness, colour);
    }

    public void drawHorizontalLine(int start, int end, int y, int thickness, int colour) {
        if (end < start) {
            int i = start;
            start = end;
            end = i;
        }
        drawRect(start, y, end + thickness, y + thickness, colour);
    }

    public void drawVerticalLine(int x, int start, int end, int thickness, int colour) {
        if (end < start) {
            int i = start;
            start = end;
            end = i;
        }
        drawRect(x, start + thickness, x + thickness, end, colour);
    }

}