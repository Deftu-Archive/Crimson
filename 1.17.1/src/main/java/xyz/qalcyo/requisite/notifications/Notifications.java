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

import gg.essential.universal.ChatColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import xyz.qalcyo.requisite.Requisite;
import xyz.qalcyo.requisite.core.data.ColourRGB;
import xyz.qalcyo.requisite.core.events.RenderTickEvent;
import xyz.qalcyo.requisite.core.notifications.INotifications;
import xyz.qalcyo.requisite.core.notifications.Notification;
import xyz.qalcyo.requisite.core.util.MathHelper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Notifications implements INotifications {

    private final List<Notification> notifications = new CopyOnWriteArrayList<>();

    private final Requisite requisite;

    public Notifications(Requisite requisite) {
        this.requisite = requisite;
        requisite.getEventBus().register(RenderTickEvent.class, this::onRenderTick);
    }

    public void push(@NotNull Notification notification) {
        notifications.add(notification);
    }

    public void render(float ticks) {
        int scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

        float y = 5;
        for (Notification notification : notifications) {
            if (notifications.indexOf(notification) > 2)
                continue;

            if (notification.getData().getX() < 1)
                notification.getData().setX(scaledWidth);

            int duration = (notification.getDuration() == -1 ? 4 : notification.getDuration());

            /* Text. */
            String title = ChatColor.BOLD + notification.getTitle();
            float width = 225;
            List<String> wrappedTitle = requisite.getEnhancedFontRenderer().wrapTextLines(title, (int) (width - 10), " ");
            List<String> wrappedDescription = requisite.getEnhancedFontRenderer().wrapTextLines(notification.getDescription(), (int) (width - 10), " ");
            int textLines = wrappedTitle.size() + wrappedDescription.size();

            /* Size and positon. */
            float height = 18 + (textLines * requisite.getEnhancedFontRenderer().getFontHeight());
            notification.getData().setX(MathHelper.INSTANCE.lerp(notification.getData().getX(), scaledWidth - width - 5, ticks / 4));
            float x = notification.getData().getX();
            if (notification.getData().getClosing() && notification.getData().getTime() < 0.75f) {
                notification.getData().setX(MathHelper.INSTANCE.lerp(notification.getData().getX(), scaledWidth + width, ticks / 4));
                x = notification.getData().getX();
            }

            /* Mouse handling. */
            float mouseX = (float) requisite.getMouseHelper().getMouseX();
            float mouseY = (float) requisite.getMouseHelper().getMouseY();
            boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            if (hovered && !notification.getData().getClicked() && requisite.getMouseHelper().isMouseButtonDown()) {
                notification.getData().setClicked(true);
                notification.click();
                notification.getData().setClosing(true);
            }

            MatrixStack matrices = new MatrixStack();

            matrices.push();

            ColourRGB backgroundColour = notification.getColour() == null || notification.getColour().getBackground() == null ? new ColourRGB(0, 0, 0, 200) : notification.getColour().getBackground().setA_builder(200);
            requisite.getRenderHelper().drawRectEnhanced((int) x, (int) y, (int) width, (int) height, backgroundColour.getRGBA());
            ColourRGB foregroundColour = notification.getColour() == null || notification.getColour().getForeground() == null ? new ColourRGB(255, 175, 0, 200) : notification.getColour().getForeground().setA_builder(200);
            requisite.getRenderHelper().drawHollowRect((int) x + 4, (int) y + 4, (int) width - 8, (int) height - 8, 1, foregroundColour.getRGBA());

            /* Text. */
            if (notification.getData().getTime() > 0.1f) {
                ColourRGB textColour = new ColourRGB(255, 255, 255, 200);
                requisite.getGlHelper().startScissorBox(x, y, width, height);
                int i = 0;
                for (String line : wrappedTitle) {
                    requisite.getEnhancedFontRenderer().drawText(line, x + 8, y + 8 + (i * 2) + (i * requisite.getEnhancedFontRenderer().getFontHeight()), textColour.getRGBA(), true);
                    i++;
                }
                for (String line : wrappedDescription) {
                    requisite.getEnhancedFontRenderer().drawText(line, x + 8, y + 8 + (i * 2) + (i * requisite.getEnhancedFontRenderer().getFontHeight()), textColour.getRGBA(), true);
                    i++;
                }
                requisite.getGlHelper().endScissorBox();
            }

            matrices.pop();

            /* Positioning. */
            y += height + 5;

            /* Other handling things. */
            if (notification.getData().getTime() >= duration)
                notification.getData().setClosing(true);
            if (!hovered)
                notification.getData().setTime((float) (notification.getData().getTime() + (notification.getData().getClosing() ? -0.02 : 0.02) * (ticks * 3)));
            if (notification.getData().getClosing() && notification.getData().getTime() <= 0)
                notifications.remove(notification);
        }
    }

    private void onRenderTick(RenderTickEvent event) {
        render(event.partialTicks);
    }

}