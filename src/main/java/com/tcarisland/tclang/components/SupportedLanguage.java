package com.tcarisland.tclang.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@RequiredArgsConstructor
@Getter
public enum SupportedLanguage {
    ENGLISH(Locale.ENGLISH),
    GERMAN(Locale.GERMAN),
    SPANISH(Locale.forLanguageTag("es")),
    FRENCH(Locale.FRENCH),
    JAPANESE(Locale.JAPANESE),
    NORWEGIAN(Locale.forLanguageTag("no"));

    final Locale locale;
}
