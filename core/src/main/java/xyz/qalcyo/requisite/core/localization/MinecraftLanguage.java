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
    FINNISH("fi_FI"),
    FILIPINO("fil_PH"),
    FAROESE("fo_FO"),

    CANADIAN_FRENCH("fr_CA"),
    FRENCH("fr_FR"),

    EAST_FRANCONIAN("fra_DE"),
    FRISIAN("fy_NL"),
    IRISH("ga_IE"),
    SCOTTISH_GAELIC("gd_GB"),
    GALICIAN("gl_ES"),
    HAWAIIAN("haw_US"),
    HEBREW("he_IL"),
    HINDI("hi_IN"),
    CROATIAN("hr_HR"),
    HUNGARIAN("hu_HU"),
    ARMENIAN("hy_AM"),
    INDONESIAN("id_ID"),
    IGBO("ig_NG"),
    IDO("io_EN"),
    ICELANDIC("is_IS"),
    INTERSLAVIC("isv"),
    ITALIAN("it_IT"),
    JAPANESE("ja_JP"),
    LOJBAN("jbo_EN"),
    GEORGIAN("ka_GE"),
    KAZAKH("kk_KZ"),
    KANNADA("kn_IN"),
    KOREAN("ko_KR"),
    RIPURIAN("ksh"),
    CORNISH("kw_GB"),
    LATIN("la_LA"),
    LUXEMBOURGISH("lb_LU"),
    LIMBURGISH("li_LI"),
    LOMBARD("lmo_IT"),
    LOLCAT("lol_US"),
    LITHUANIAN("lt_LT"),
    LATVIAN("lv_LV"),
    CLASSICAL_CHISE("lzh"),
    MACEDONIAN("mk_MK"),
    MONGOLIAN("mn_MN"),
    MALAY("ms_MY"),
    MALTESE("mt_MT"),
    LOW_GERMAN("nds_DE"),

    FLEMISH("nl_BE"),
    DUTCH("nl_NL"),

    NORWEGIAN_NYNORSK("nn_NO"),
    NORWEGIAN_BOKMAL("nn_NO"),

    OCCITAN("oc_FR"),
    ELFDALIAN("ovd"),
    POLISH("pl_PL"),

    BRAZILIAN_PORTUGUESE("pt_BR"),
    PORTUGUEASE("pt_PT"),

    QUENYA("qya_AA"),
    ROMAINIAN("ro_RO"),

    PRE_REVOLUTIONARY_RUSSIAN("rpr"),
    RUSSIAN("ru_RU"),

    NORTHERN_SAMI("se_NO"),
    SLOVAK("sk_SK"),
    SLOVENIAN("sl_SI"),
    SOMALI("so_SO"),
    ALBANIAN("sq_AL"),
    SERBIAN("sr_SP"),
    SWEDISH("sv_SE"),
    UPPER_SAXON_GERMAN("sxu"),
    SILESIAN("szi"),
    TAMIL("ta_IN"),
    THAI("th_TH"),
    TAGALOG("tl_PH"),
    KLINGON("tlh_AA"),
    TOKI_PONA("tok"),
    TURKISH("tr_TR"),
    TATAR("tt_RU"),
    UKRAINIAN("uk_UA"),
    VALENCIAN("val_ES"),
    VENETIAN("vec_IT"),
    VIETNAMESE("vi_VN"),
    YIDDISH("yi_DE"),
    YORUBA("yo_NG"),

    CHINESE_SIMPLIFIED("zh_CN"),
    HONH_KONG_CHINESE("zh_HK"),
    TAIWAN_CHINESE("zh_TW");

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