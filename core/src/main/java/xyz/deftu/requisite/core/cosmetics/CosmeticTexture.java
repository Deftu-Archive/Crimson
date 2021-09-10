package xyz.deftu.requisite.core.cosmetics;

import java.io.InputStream;

public class CosmeticTexture {
    public final String name;
    public final InputStream texture;

    public CosmeticTexture(String name, InputStream texture) {
        this.name = name;
        this.texture = texture;
    }
}