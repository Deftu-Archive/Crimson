package xyz.matthewtgm.requisite;

import lombok.Getter;
import net.minecraft.util.ResourceLocation;
import xyz.matthewtgm.requisite.util.ResourceHelper;

public class RequisiteResources {

   @Getter private static final GuiResources guiResources = new GuiResources();

    public static class GuiResources {
        public final String basePath = "gui/icons/";
        public ResourceLocation getCosmeticsIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "cosmetics_icon.png");
        }
        public ResourceLocation getExitIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "exit_icon.png");
        }
        public ResourceLocation getKeyBindsIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "keybinds_icon.png");
        }
        public ResourceLocation getRefreshIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "refresh_icon.png");
        }
        public ResourceLocation getSettingsIcon() {
            return ResourceHelper.get(Requisite.ID, basePath + "settings_icon.png");
        }
    }

}