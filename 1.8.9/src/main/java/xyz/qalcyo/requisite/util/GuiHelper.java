package xyz.qalcyo.requisite.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GuiHelper {

    private GuiScreen awaitingDisplay = new GuiNull();

    public GuiHelper() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void open(GuiScreen screen) {
        awaitingDisplay = screen;
    }

    @SubscribeEvent
    protected void onClientTick(TickEvent.ClientTickEvent event) {
        if (!(awaitingDisplay instanceof GuiNull)) {
            Minecraft.getMinecraft().displayGuiScreen(awaitingDisplay);
            awaitingDisplay = new GuiNull();
        }
    }

    private static class GuiNull extends GuiScreen {}

}