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

package xyz.qalcyo.requisite.core.integration.discord;

import de.jcm.discordgamesdk.Core;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DiscordNativeLoader {

    public void initialize() {
        String fileName = SystemUtils.IS_OS_WINDOWS ? "discord_game_sdk.dll" : SystemUtils.IS_OS_MAC ? "discord_game_sdk.dylib" : SystemUtils.IS_OS_LINUX ? "discord_game_sdk.so" : null;
        if (fileName == null)
            throw new IllegalArgumentException("Cannot determine current operating system.");

        File nativeFile = new File("Requisite/Discord/natives", fileName);
        File jni = new File("Requisite/Discord/natives/", ((SystemUtils.IS_OS_WINDOWS ? "discord_game_sdk_jni.dll" : "libdiscord_game_sdk_jni" +
                (SystemUtils.IS_OS_MAC ? ".dylib" : ".so"))));

        if (nativeFile.exists() && jni.exists()) {
            load(nativeFile, jni);
        } else {
            File nativesDirectory = new File("Requisite/Discord/natives");
            if (!nativesDirectory.mkdirs()) {
                throw new IllegalStateException("Couldn't make directories for Requisite Discord natives.");
            }

            String arch = System.getProperty("os.arch").toLowerCase();
            if (arch.equals("amd64")) {
                arch = "x86_64";
            }

            try {
                URL downloadUrl = new URL("https://dl-game-sdk.discordapp.net/3.1.0/discord_game_sdk.zip");
                URLConnection con = downloadUrl.openConnection();
                con.setRequestProperty("User-Agent", "Requisite");
                ZipInputStream zin = new ZipInputStream(con.getInputStream());
                ZipEntry entry;
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.getName().equals("lib/" + arch + "/" + fileName)) {
                        Files.copy(zin, nativeFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        extract(nativeFile);
                        break;
                    }
                    zin.closeEntry();
                }
                zin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adapted from HyCord under GPL 3.0 license
     * https://github.com/DeDiamondPro/HyCord/blob/dev/LICENSE
     *
     * @author DeDiamondPro
     */
    public void extract(File nativeFile) throws IOException {
        String arch = System.getProperty("os.arch").toLowerCase();

        if (arch.equals("x86_64")) {
            arch = "amd64";
        }

        /**
         * Adapted from HyCord under GPL 3.0 license
         * https://github.com/DeDiamondPro/HyCord/blob/dev/LICENSE
         *
         * @author DeDiamondPro
         */
        String path = "/native/" + (SystemUtils.IS_OS_WINDOWS ? "windows" : (SystemUtils.IS_OS_MAC ? "macos" : "linux"))
                + "/" + arch + "/" + (SystemUtils.IS_OS_WINDOWS ? "discord_game_sdk_jni.dll" : "libdiscord_game_sdk_jni" +
                (SystemUtils.IS_OS_MAC ? ".dylib" : ".so"));
        InputStream input = DiscordNativeLoader.class.getResourceAsStream(path);

        File jni = new File("Requisite/Discord/natives/", (SystemUtils.IS_OS_WINDOWS ? "discord_game_sdk_jni.dll" : "libdiscord_game_sdk_jni" +
                (SystemUtils.IS_OS_MAC ? ".dylib" : ".so")));
        Files.copy(input, jni.toPath(), StandardCopyOption.REPLACE_EXISTING);

        load(nativeFile, jni);
    }

    public void load(File nativeFile, File jni) {
        if (SystemUtils.IS_OS_WINDOWS)
            System.load(nativeFile.getAbsolutePath());
        System.load(jni.getAbsolutePath());
        Core.initDiscordNative(nativeFile.getAbsolutePath());
    }

}