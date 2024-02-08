package com.tcarisland.tclang.projects.advancedhatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tcarisland.tclang.components.SupportedLanguage;
import com.tcarisland.tclang.components.TclangPreferences;
import com.tcarisland.tclang.components.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class AdvancedHatchAutoTranslator {

    private final TclangPreferences preferences;
    TranslationService translationService;

    public AdvancedHatchAutoTranslator(@Autowired TranslationService translationService, @Autowired TclangPreferences preferences) {
        this.translationService = translationService;
        this.preferences = preferences;
    }

    public void run() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        AdvancedHatchTranslationPackage translationPackage = translate();
        try {
            if(translationPackage == null) {
                return;
            }
            System.out.println(mapper.writer(SerializationFeature.INDENT_OUTPUT).writeValueAsString(translationPackage));
            mapper.writeValue(new File(String.format("src/main/resources/projects/advancedhatch/advancedhatch_translated.yml")), translationPackage);
        } catch (IOException e) {
            System.out.printf("%s%n", e.getMessage());
        }
    }

    public AdvancedHatchTranslationPackage translate() {
        AdvancedHatchTranslationPackage translationPackage = readAdvancedHatch();
        if(translationPackage == null) {
            System.out.println("translationPackage is null");
            return translationPackage;
        }
        SupportedLanguage defaultLanguage = SupportedLanguage.ENGLISH;
        for(SupportedLanguage language : SupportedLanguage.values()) {
            for(AdvancedHatchItemLabel label : translationPackage.getLabels()) {
                Map<Locale, String> updatedTranslations = label.getTranslations();
                String originalLabel = label.getTranslations().get(defaultLanguage.getLocale());
                String translatedLabel = translationService.translateText(originalLabel, defaultLanguage.getLocale(), language.getLocale(), preferences.getTranslationServiceUrl());
                System.out.printf("%s : %s - %s%n", language.glyphsTag(), originalLabel, translatedLabel);
                updatedTranslations.put(language.getLocale(), translatedLabel);
                label.setTranslations(updatedTranslations);
            }
        }
        return translationPackage;
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
