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

package xyz.qalcyo.requisite.notifications;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.core.data.ColourRGB;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Notifications implements INotifications {

    private final List<Notification> notifications = new CopyOnWriteArrayList<>();

    private final Requisite requisite;

    public Notifications(Requisite requisite) {
        this.requisite = requisite;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void push(Notification notification) {
        notifications.add(notification);
    }

    public void render(float ticks) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaledWidth = scaledResolution.getScaledWidth();
        int scaledHeight = scaledResolution.getScaledHeight();

        float y = 5;
        for (Notification notification : notifications) {
            if (notifications.indexOf(notification) > 2)
                continue;

            if (notification.data.x < 1)
                notification.data.x = scaledWidth;

            int duration = (notification.duration == -1 ? 4 : notification.duration);

            /* Text. */
            String title = ChatColour.BOLD + notification.title;
            float width = 225;
            List<String> wrappedTitle = requisite.getEnhancedFontRenderer().wrapTextLines(title, (int) (width - 10), " ");
            List<String> wrappedDescription = requisite.getEnhancedFontRenderer().wrapTextLines(notification.description, (int) (width - 10), " ");
            int textLines = wrappedTitle.size() + wrappedDescription.size();

            /* Size and positon. */
            float height = 18 + (textLines * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT);
            float x = notification.data.x = requisite.getMathHelper().lerp(notification.data.x, scaledWidth - width - 5, ticks / 4);
            if (notification.data.closing && notification.data.time < 0.75f)
                x = notification.data.x = requisite.getMathHelper().lerp(notification.data.x, scaledWidth + width, ticks / 4);

            /* Mouse handling. */
            float mouseX = (float) requisite.getMouseHelper().getMouseX();
            float mouseY = (float) requisite.getMouseHelper().getMouseY();
            boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            if (hovered && !notification.data.clicked && requisite.getMouseHelper().isMouseButtonDown()) {
                notification.data.clicked = true;
                notification.click();
                notification.data.closing = true;
            }

            /* Rendering. */
            GlStateManager.pushMatrix();
            ColourRGB backgroundColour = notification.colour == null || notification.colour.background == null ? new ColourRGB(0, 0, 0, 200) : notification.colour.background.setA_builder(200);
            requisite.getRenderHelper().drawRectEnhanced((int) x, (int) y, (int) width, (int) height, backgroundColour.getRGBA());
            ColourRGB foregroundColour = notification.colour == null || notification.colour.foreground == null ? new ColourRGB(255, 175, 0, 200) : notification.colour.foreground.setA_builder(200);
            requisite.getRenderHelper().drawHollowRect((int) x + 4, (int) y + 4, (int) width - 8, (int) height - 8, 1, foregroundColour.getRGBA());

            /* Text. */
            if (notification.data.time > 0.1f) {
                ColourRGB textColour = new ColourRGB(255, 255, 255, 200);
                requisite.getGlHelper().startScissorBox(x, y, width, height);
                int i = 0;
                for (String line : wrappedTitle) {
                    requisite.getEnhancedFontRenderer().drawText(line, x + 8, y + 8 + (i * 2) + (i * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT), textColour.getRGBA(), true);
                    i++;
                }
                for (String line : wrappedDescription) {
                    requisite.getEnhancedFontRenderer().drawText(line, x + 8, y + 8 + (i * 2) + (i * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT), textColour.getRGBA(), true);
                    i++;
                }
                requisite.getGlHelper().endScissorBox();
            }
            GlStateManager.popMatrix();

            /* Positioning. */
            y += height + 5;

            /* Other handling things. */
            if (notification.data.time >= duration)
                notification.data.closing = true;
            if (!hovered)
                notification.data.time += (notification.data.closing ? -0.02 : 0.02) * (ticks * 3);
            if (notification.data.closing && notification.data.time <= 0)
                notifications.remove(notification);
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            render(event.renderTickTime);
        }
    }

}