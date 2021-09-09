package xyz.deftu.requisite.notifications;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import xyz.deftu.requisite.Requisite;
import xyz.deftu.requisite.core.data.ColourRGB;
import xyz.deftu.requisite.core.events.RenderTickEvent;
import xyz.deftu.requisite.core.notifications.INotifications;
import xyz.deftu.requisite.core.notifications.Notification;
import xyz.deftu.requisite.core.notifications.NotificationColour;
import xyz.deftu.requisite.core.util.ChatColour;
import xyz.matthewtgm.simpleeventbus.EventSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class Notifications implements INotifications {

    private final List<Notification> notifications = new CopyOnWriteArrayList<>();

    private final Requisite requisite;

    public Notifications(Requisite requisite) {
        this.requisite = requisite;
        requisite.getManager().getEventBus().register(this);
    }

    public void push(String title, String description, NotificationColour colour, int duration, Consumer<Notification> clickListener) {
        notifications.add(new Notification(title, description, colour, duration, clickListener));
    }

    public void push(String title, String description, Consumer<Notification> clickListener) {
        notifications.add(new Notification(title, description, clickListener));
    }

    public void push(String title, String description, int duration) {
        notifications.add(new Notification(title, description, duration));
    }

    public void push(String title, String description, NotificationColour colour) {
        notifications.add(new Notification(title, description, colour));
    }

    public void push(String title, String description, NotificationColour colour, int duration) {
        notifications.add(new Notification(title, description, colour, duration));
    }

    public void push(String title, String description, int duration, Consumer<Notification> clickListener) {
        notifications.add(new Notification(title, description, duration, clickListener));
    }

    public void push(String title, String description, NotificationColour colour, Consumer<Notification> clickListener) {
        notifications.add(new Notification(title, description, colour, clickListener));
    }

    public void push(String title, String description) {
        notifications.add(new Notification(title, description));
    }

    public void render(float ticks) {
        int scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int scaledHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();

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
            List<String> wrappedTitle = requisite.getManager().getEnhancedFontRenderer().wrapTextLines(title, (int) (width - 10), " ");
            List<String> wrappedDescription = requisite.getManager().getEnhancedFontRenderer().wrapTextLines(notification.description, (int) (width - 10), " ");
            int textLines = wrappedTitle.size() + wrappedDescription.size();

            /* Size and positon. */
            float height = 18 + (textLines * requisite.getManager().getEnhancedFontRenderer().getFontHeight());
            float x = notification.data.x = requisite.getManager().getMathHelper().lerp(notification.data.x, scaledWidth - width - 5, ticks / 4);
            if (notification.data.closing && notification.data.time < 0.75f)
                x = notification.data.x = requisite.getManager().getMathHelper().lerp(notification.data.x, scaledWidth + width, ticks / 4);

            /* Mouse handling. */
            float mouseX = (float) requisite.getManager().getMouseHelper().getMouseX();
            float mouseY = (float) requisite.getManager().getMouseHelper().getMouseY();
            boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            if (hovered && !notification.data.clicked && requisite.getManager().getMouseHelper().isMouseButtonDown()) {
                notification.data.clicked = true;
                notification.click();
                notification.data.closing = true;
            }

            MatrixStack matrices = new MatrixStack();

            matrices.push();

            ColourRGB backgroundColour = notification.colour == null || notification.colour.background == null ? new ColourRGB(0, 0, 0, 200) : notification.colour.background.setA_builder(200);
            requisite.getManager().getRenderHelper().drawRectEnhanced((int) x, (int) y, (int) width, (int) height, backgroundColour.getRGBA());
            ColourRGB foregroundColour = notification.colour == null || notification.colour.foreground == null ? new ColourRGB(255, 175, 0, 200) : notification.colour.foreground.setA_builder(200);
            requisite.getManager().getRenderHelper().drawHollowRect((int) x + 4, (int) y + 4, (int) width - 8, (int) height - 8, 1, foregroundColour.getRGBA());

            /* Text. */
            if (notification.data.time > 0.1f) {
                ColourRGB textColour = new ColourRGB(255, 255, 255, 200);
                requisite.getManager().getGlHelper().startScissorBox(x, y, width, height);
                int i = 0;
                for (String line : wrappedTitle) {
                    requisite.getManager().getEnhancedFontRenderer().drawText(line, x + 8, y + 8 + (i * 2) + (i * requisite.getManager().getEnhancedFontRenderer().getFontHeight()), textColour.getRGBA(), true);
                    i++;
                }
                for (String line : wrappedDescription) {
                    requisite.getManager().getEnhancedFontRenderer().drawText(line, x + 8, y + 8 + (i * 2) + (i * requisite.getManager().getEnhancedFontRenderer().getFontHeight()), textColour.getRGBA(), true);
                    i++;
                }
                requisite.getManager().getGlHelper().endScissorBox();
            }

            matrices.pop();

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

    @EventSubscriber
    private void onRenderTick(RenderTickEvent event) {
        render(event.partialTicks);
    }

}