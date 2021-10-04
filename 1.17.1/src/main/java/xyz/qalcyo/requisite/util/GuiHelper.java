package xyz.qalcyo.requisite.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.events.TickEvent;
import xyz.qalcyo.requisite.core.util.IGuiHelper;

public class GuiHelper implements IGuiHelper<Screen> {
    private boolean awaiting = false;
    private Screen awaitingDisplay = null;

    public GuiHelper(RequisiteAPI requisite) {
        requisite.getEventBus().register(TickEvent.class, this::onClientTick);
    }

    public void open(Screen screen) {
        awaitingDisplay = screen;
        awaiting = true;
    }

    public boolean isAwaiting() {
        return awaiting;
    }

    public Screen getAwaiting() {
        return awaitingDisplay;
    }

    protected void onClientTick(TickEvent event) {
        if (awaiting) {
            MinecraftClient.getInstance().setScreen(awaitingDisplay);
            awaitingDisplay = null;
            awaiting = false;
        }
    }

}
