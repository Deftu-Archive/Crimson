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

public enum MinecraftLanguage {

    UNKNOWN("unknown"),

    AFRIKAANS("af_ZA"),
    ARABIC("ar_SA"),
    ASTURIAN("ast_ES"),
    AZERBAIJANI("az_AZ"),
    BASHKIR("ba_RU"),
    BAVARIAN("bar"),
    BELARUSIAN("be_BY"),
    BULGARIAN("bg_BG"),
    BRETON("br_FR"),
    BRABANTIAN("brb"),
    BOSNIAN("bs_BA"),
    CATALAN("ca_ES"),
    CZECH("cs_CZ"),
    WELSH("cy_GB"),
    DANISH("da_DK"),

    AUSTRIAN_GERMAN("de_AT"),
    SWISS_GERMAN("de_CH"),
    GERMAN("de_DE"),

    GREEK("el_GR"),

    AUSTRALIAN_ENGLISH("en_AU"),
    CANADIAN_ENGLISH("en_CA"),
    BRITISH_ENGLISH("en_GB"),
    NEW_ZEALAND_ENGLISH("en_NZ"),
    PIRATE_ENGLISH("en_PT"),
    UPSIDE_DOWN_ENGLISH("en_UD"),
    AMERICAN_ENGLISH("en_US"),
    ANGLISH("enp"),
    SHAKESPEAREAN_ENGLISH("enws"),

    ESPERANTO("eo_UY"),

    ARGENTINIAN_SPANISH("es_AR"),
    CHILEAN_SPANISH("es_CL"),
    ECUADORIAN_SPANISH("es_EC"),
    SPANISH("es_ES"),
    MEXICAN_SPANISH("es_MX"),
    URUGUAYAN_SPANISH("es_UY"),
    VENEZUELAN_SPANISH("es_VE"),

    ANDALUSIAN("esan"),
    ESTONIAN("et_EE"),
    BASQUE("eu_ES"),
    PERSIAN("fa_IR"),
    FINNISH("fi_FI");

    private final String languageCode;
    MinecraftLanguage(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public static MinecraftLanguage fromLanguageCode(String languageCode) {
        for (MinecraftLanguage value : values()) {
            if (value.getLanguageCode().equalsIgnoreCase(languageCode)) {
                return value;
            }
        }

        return MinecraftLanguage.UNKNOWN;
    }

}