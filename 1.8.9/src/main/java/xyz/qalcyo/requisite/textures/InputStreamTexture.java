/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

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