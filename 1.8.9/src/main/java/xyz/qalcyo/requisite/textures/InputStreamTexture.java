package xyz.qalcyo.requisite.textures;

import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamTexture extends AbstractTexture {

    private final InputStream inputStream;

    public InputStreamTexture(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void loadTexture(IResourceManager resourceManager) throws IOException {
        deleteGlTexture();
        try {
            TextureUtil.uploadTextureImageAllocate(getGlTextureId(), TextureUtil.readBufferedImage(inputStream), false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}