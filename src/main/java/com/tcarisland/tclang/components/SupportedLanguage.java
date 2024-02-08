package com.tcarisland.tclang.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

@RequiredArgsConstructor
@Getter
public enum SupportedLanguage {
    CZECH(Locale.forLanguageTag("cs"), true),
    GERMAN(Locale.GERMAN, true),
    ENGLISH(Locale.ENGLISH, true),
    SPANISH(Locale.forLanguageTag("es"), true),
    FRENCH(Locale.FRENCH, true),
    ITALIAN(Locale.ITALIAN, true),
    JAPANESE(Locale.JAPANESE, true),
    KOREAN(Locale.KOREAN, true),
    PORTUGUESE(Locale.forLanguageTag("pt"), true),
    RUSSIAN(Locale.forLanguageTag("ru"), true),
    TURKISH(Locale.forLanguageTag("tr"), true),
    CHINESE(Locale.forLanguageTag("zh-CN"), false),
    NORWEGIAN(Locale.forLanguageTag("nb-NO"), true);

    final Locale locale;
    final boolean singleLanguageTag;

    public String glyphsTag() {
        if(singleLanguageTag) {
            if(StringUtils.isEmpty(locale.getCountry())) {
                return StringUtils.lowerCase(this.locale.toLanguageTag());
            } else {
                return StringUtils.lowerCase(this.locale.getCountry());
            }
        } else {
            return String.format("%s_%s", StringUtils.lowerCase(locale.getLanguage()), StringUtils.upperCase(locale.getCountry()));
        }
    }
}
