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

package xyz.qalcyo.requisite.core.keybinds;

import xyz.qalcyo.requisite.core.localization.ILocalizationReloadable;
import xyz.qalcyo.requisite.core.localization.ModLocalization;

/**
 * Main class for Requisite's {@link KeyBind} API handling.
 */
public abstract class KeyBind implements ILocalizationReloadable {

    private final String name, category;
    private int key;

    private String translationParent;
    private String translationKey;
    private String translatedCategory;

    public KeyBind(String name, String category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
    }

    /**
     * Handles key presses with the state.
     *
     * @param state The state of the key during this input.
     */
    public abstract void handle(KeyBindState state);

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTranslatedCategory() {
        return translatedCategory;
    }

    public void setTranslatedCategory(ModLocalization localization, String parent, String key) {
        this.translationParent = parent;
        this.translationKey = key;

        localization.registerReloadable(this);

        this.translatedCategory = localization.translate(parent, key);
    }

    public void setTranslatedCategory(ModLocalization localization, String key) {
        setTranslatedCategory(localization, null, key);
    }

    public void reloadLocalization(ModLocalization localization) {
        setTranslatedCategory(localization, translationParent, translationKey);
    }

}