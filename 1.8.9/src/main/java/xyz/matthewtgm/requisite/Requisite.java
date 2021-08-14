package xyz.matthewtgm.requisite;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.matthewtgm.requisite.core.IRequisite;
import xyz.matthewtgm.requisite.core.IRequisiteManager;

import java.io.File;

@Mod(
        name = "@NAME@",
        version = "@VER@",
        modid = "@ID@",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Requisite implements IRequisite {

    private static final Requisite instance = new Requisite();
    private RequisiteManager manager;

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event) {
        System.out.println("Cool!");
        initialize(Minecraft.getMinecraft().mcDataDir);
    }

    public void initialize(File gameDirectory) {
        if (manager == null)
            manager = new RequisiteManager();

        manager.initialize(this, gameDirectory);
    }

    public RequisiteManager getManager() {
        return manager;
    }

    public static Requisite getInstance() {
        return instance;
    }

}