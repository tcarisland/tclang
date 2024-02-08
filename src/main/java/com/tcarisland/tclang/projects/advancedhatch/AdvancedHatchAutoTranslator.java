package com.tcarisland.tclang.projects.advancedhatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tcarisland.tclang.components.SupportedLanguage;
import com.tcarisland.tclang.components.TclangPreferences;
import com.tcarisland.tclang.components.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Service
public class AdvancedHatchAutoTranslator {

    private final TclangPreferences preferences;
    TranslationService translationService;

    public AdvancedHatchAutoTranslator(@Autowired TranslationService translationService, @Autowired TclangPreferences preferences) {
        this.translationService = translationService;
        this.preferences = preferences;
    }

    public void run() {
        System.out.printf("%s", preferences.getTranslationServiceUrl());
        AdvancedHatchTranslationPackage translationPackage = readAdvancedHatch();
        if(translationPackage == null) {
            System.out.println("translationPackage is null");
            return;
        }
        SupportedLanguage defaultLanguage = SupportedLanguage.ENGLISH;
        for(SupportedLanguage language : SupportedLanguage.values()) {
            for(AdvancedHatchItemLabel label : translationPackage.getLabels()) {
                String originalLabel = label.getTranslations().get(defaultLanguage.getLocale());
                String translatedLabel = translationService.translateText(originalLabel, defaultLanguage.getLocale(), language.getLocale(), preferences.getTranslationServiceUrl());
                System.out.printf("TRANSLATION %s %s %s %s%n", label.getName(), language.getLocale().toLanguageTag(), originalLabel, translatedLabel);
            }
        }
    }
    public AdvancedHatchTranslationPackage readAdvancedHatch() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            AdvancedHatchTranslationPackage advancedHatchTranslationPackage = mapper
                    .readValue(
                            new File("src/main/resources/projects/advancedhatch/advancedhatch.yml"),
                            AdvancedHatchTranslationPackage.class
                    );
            return advancedHatchTranslationPackage;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
