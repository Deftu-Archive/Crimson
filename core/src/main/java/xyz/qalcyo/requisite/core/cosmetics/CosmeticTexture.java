package xyz.qalcyo.requisite.core.cosmetics;

import xyz.qalcyo.json.entities.JsonObject;

import java.io.InputStream;

public class CosmeticTexture {
    public final String name;
    public final InputStream texture;

    public CosmeticTexture(String name, InputStream texture) {
        this.name = name;
        this.texture = texture;
    }

    public String toString() {
        return new JsonObject()
                .add("name", name)
                .add("texture", texture)
                .getAsString();
    }

}