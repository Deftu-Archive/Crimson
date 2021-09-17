package xyz.qalcyo.requisite.core.cosmetics;

import java.util.List;

public class PlayerCosmeticData {

    private final List<CosmeticData> owned;
    private final List<CosmeticData> enabled;

    public PlayerCosmeticData(List<CosmeticData> owned, List<CosmeticData> enabled) {
        this.owned = owned;
        this.enabled = enabled;
    }

    public List<CosmeticData> getOwned() {
        return owned;
    }

    public List<CosmeticData> getEnabled() {
        return enabled;
    }

}