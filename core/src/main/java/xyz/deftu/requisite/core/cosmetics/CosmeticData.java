package xyz.deftu.requisite.core.cosmetics;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CosmeticData {

    private final String name, id;
    private final CosmeticType type;
    private final CosmeticTexture texture;
    private final List<CosmeticFlag> flags;

    CosmeticData(String name, String id, CosmeticType type, CosmeticTexture texture, CosmeticFlag... flags) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.texture = texture;

        this.flags = new LinkedList<>();
        this.flags.addAll(Arrays.asList(flags));
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isId(String id) {
        return this.id != null && this.id.equals(id);
    }

    public CosmeticType getType() {
        return type;
    }

    public boolean isType(CosmeticType type) {
        return this.type == type;
    }

    public CosmeticTexture getTexture() {
        return texture;
    }

    public List<CosmeticFlag> getFlags() {
        return flags;
    }

    public boolean hasFlag(CosmeticFlag flag) {
        return this.flags.contains(flag);
    }

    public static CosmeticData from(String name, String id, CosmeticType type, CosmeticTexture texture, CosmeticFlag... flags) {
        return new CosmeticData(name, id, type, texture, flags);
    }

    public static CosmeticData from(String name, CosmeticType type, CosmeticTexture texture, CosmeticFlag... flags) {
        return from(name, name.toUpperCase().replace(' ', '_'), type, texture, flags);
    }

}