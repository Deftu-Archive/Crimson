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

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.launchwrapper.Launch;
import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.util.GsonHelper;
import xyz.qalcyo.requisite.core.RequisiteAPI;
import xyz.qalcyo.requisite.core.bridge.minecraft.IResourceReloadBridge;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ModLocalization implements IResourceReloadBridge {

    private static final JsonParser JSON_PARSER = new JsonParser();

    private final String modName;

    private String defaultLanguage;
    private JsonObject currentContent;

    ModLocalization(String modName, String defaultLanguage) {
        this.modName = modName;
        this.defaultLanguage = defaultLanguage;

        RequisiteAPI.retrieveInstance().getBridge().getMinecraftBridge().registerReloadListener(this);

        syncWithMinecraft();
    }

    public String translate(String parent, String key, String... replacements) {
        if (currentContent == null) {
            return "Translations unavailable.";
        }

        if (parent != null && !currentContent.hasKey(parent)) {
            return "Translation not found.";
        }

        String translation = parent == null ? currentContent.getAsString(key) : currentContent.getAsObject(parent).getAsString(key);
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

    public String getModName() {
        return modName;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public JsonObject getCurrentLanguage() {
        return currentContent.copy();
    }

    public void syncWithMinecraft() {
        setCurrentLanguage(RequisiteAPI.retrieveInstance().getBridge().getMinecraftBridge().getLanguageCode());
    }

    public void setCurrentLanguage(String languageCode) {
        try {
            InputStream resource = Launch.classLoader.getResourceAsStream(modName + "/" + languageCode + ".json");
            JsonElement element = JSON_PARSER.parse(new InputStreamReader(resource));
            if (element.isJsonObject()) {
                currentContent = GsonHelper.convert(element).getAsJsonObject();
            } else if (!element.isJsonObject()) {
                throw new UnsupportedOperationException("Language files MUST be a JSON object!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            setCurrentLanguage(defaultLanguage);
        }
    }

    public void reloadResources() {
        syncWithMinecraft();
    }

}