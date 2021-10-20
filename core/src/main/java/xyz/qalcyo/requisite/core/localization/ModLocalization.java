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

package xyz.qalcyo.requisite.core.localization;

import xyz.qalcyo.json.entities.JsonElement;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.parser.JsonParser;
import xyz.qalcyo.mango.IO;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.bridge.minecraft.IResourceReloadBridge;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ModLocalization implements IResourceReloadBridge {

    private int fails;

    private final String path;

    private MinecraftLanguage defaultLanguage;
    private JsonObject currentContent;

    private List<ILocalizationReloadable> reloadables = new ArrayList<>();

    ModLocalization(String path, MinecraftLanguage defaultLanguage) {
        this.path = path;
        this.defaultLanguage = defaultLanguage;

        RequisiteAPI.retrieveInstance().getBridge().getMinecraftBridge().registerReloadListener(this);

        sync();
    }

    public String translate(String parent, String key, String... replacements) {
        if (currentContent == null) {
            return "Translations unavailable.";
        }

        if (parent != null && !currentContent.hasKey(parent)) {
            return "Translation not found.";
        }

        String translation = parent == null ? currentContent.getAsString(key) : currentContent.getAsObject(parent).getAsString(key);
        System.out.println(currentContent);
        if (translation == null) {
            return "Translation not found.";
        }

        if (replacements != null) {
            for (String replacement : replacements) {
                translation = translation.replaceFirst("\\{}", replacement);
            }
        }

        return translation;
    }

    public String translate(String parent, String key, List<String> replacements) {
        return translate(parent, key, replacements.toArray(new String[0]));
    }

    public String translate(String parent, String key) {
        return translate(parent, key, (String[]) null);
    }

    public String translate(String key, String... replacements) {
        return translate(null, key, replacements);
    }

    public String translate(String key) {
        return translate(key, (String[]) null);
    }

    public void registerReloadable(ILocalizationReloadable reloadable) {
        if (!reloadables.contains(reloadable)) {
            reloadables.add(reloadable);
        }
    }

    public String getPath() {
        return path;
    }

    public MinecraftLanguage getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(MinecraftLanguage defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public JsonObject getCurrentLanguage() {
        return currentContent.copy();
    }

    public void sync() {
        setCurrentLanguage(RequisiteAPI.retrieveInstance().getBridge().getMinecraftBridge().getLanguageEnum());
    }

    public void setCurrentLanguage(MinecraftLanguage languageCode) {
        try {
            String path = this.path;
            path = path.startsWith("/") ? path : "/" + path;
            path = path.endsWith("/") ? path : path + "/";
            InputStream resource = getClass().getResourceAsStream(path + languageCode.getLanguageCode() + ".json");
            if (resource == null && fails > 1) {
                throw new IllegalStateException("Language file resource is null...");
            }

            JsonElement element = JsonParser.parse(IO.toString(resource));
            if (element.isJsonObject()) {
                currentContent = element.getAsJsonObject();

                for (ILocalizationReloadable reloadable : reloadables) {
                    reloadable.reloadLocalization(this);
                }

                resource.close();
            } else {
                throw new UnsupportedOperationException("Requisite language files' content MUST be a JSON object.");
            }
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                throw new RuntimeException(e);
            }

            fails++;

            RequisiteAPI requisite = RequisiteAPI.retrieveInstance();
            if (requisite != null && requisite.getLogger() != null) {
                requisite.getLogger().info("Failed to load language file... (" + languageCode + " - " + languageCode.getLanguageCode() + ")");
            }

            e.printStackTrace();
            setCurrentLanguage(defaultLanguage);
        }
    }

    public void reloadResources() {
        sync();
    }

}