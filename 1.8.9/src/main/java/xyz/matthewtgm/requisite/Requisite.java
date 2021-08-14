package xyz.matthewtgm.requisite;

import net.minecraftforge.fml.common.Mod;
import xyz.matthewtgm.requisite.core.IRequisite;

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