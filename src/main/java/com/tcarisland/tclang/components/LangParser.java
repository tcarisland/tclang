package com.tcarisland.tclang.components;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class LangParser {

    @Autowired
    private TclangPreferences preferences;

    public void run() {
        System.out.printf("outputPath: %s%n", preferences.getOutputPath());
        for(SupportedLanguage lang : SupportedLanguage.values()) {
            System.out.printf("LANGUAGE %s %n", lang.getLocale().getLanguage());
        }
    }
}
