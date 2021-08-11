package xyz.matthewtgm.requisite;

import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import xyz.matthewtgm.requisite.util.ResourceHelper;

public class RequisiteResources {

   @Getter private static final GuiResources guiResources = new GuiResources();

    public static class GuiResources {
        public final String basePath = "gui/icons/";
        public ResourceLocation getExitIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "exit.png");
        }
        public ResourceLocation getRefreshIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "refresh.png");
        }
    }

}