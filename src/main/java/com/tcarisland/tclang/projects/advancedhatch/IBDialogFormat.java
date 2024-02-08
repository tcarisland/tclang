package com.tcarisland.tclang.projects.advancedhatch;

import com.tcarisland.tclang.components.SupportedLanguage;

import java.util.Locale;

public class IBDialogFormat {

    public static String format(AdvancedHatchItemLabel label, Locale locale) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("/* \"%s\" */%n", label.getTranslations().get(Locale.ENGLISH)));
        sb.append(String.format("\"%s.title\" = \"%s\";%n%n", label.getDestination(), label.getTranslations().get(locale)));
        return sb.toString();
    }

}
