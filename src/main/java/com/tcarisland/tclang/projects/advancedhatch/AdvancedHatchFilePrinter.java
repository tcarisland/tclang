package com.tcarisland.tclang.projects.advancedhatch;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tcarisland.tclang.components.SupportedLanguage;
import com.tcarisland.tclang.components.TclangPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdvancedHatchFilePrinter {

    @Autowired
    TclangPreferences preferences;

    public void run() {
        AdvancedHatchTranslationPackage translationPackage = AdvancedHatchAutoTranslator.readAdvancedHatch("src/main/resources/projects/advancedhatch/advancedhatch_translated.yml");
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        Path basePath = Paths.get(preferences.getOutputPath(), "AdvancedHatch");
        try {
            Files.createDirectories(basePath);
            printLabels(basePath, translationPackage);
            printPythonLabels(basePath, translationPackage);
        } catch (Exception e) {
            System.out.printf("%s%n", e.getMessage());
        }
    }

    private void printPythonLabels(Path basePath, AdvancedHatchTranslationPackage translationPackage) throws FileNotFoundException {
        Path filePath = Paths.get(basePath.toString(), "labels.py");
        PrintWriter out = new PrintWriter(filePath.toFile());
        for(AdvancedHatchItemLabel label : translationPackage.getPythonLabels()) {
            out.println("self." + label.getDestination() + " = Glyphs.localize({");
            for(SupportedLanguage lang : SupportedLanguage.values()) {
                String line = String.format("'%s': '%s',", lang.glyphsTag(), label.getTranslations().get(lang.getLocale()));
                out.println("\t" + line);
            }
            out.println("})");
            out.println();
        }
        out.close();
    }

    private void printLabels(Path basePath, AdvancedHatchTranslationPackage translationPackage) throws IOException {
        for(SupportedLanguage language : SupportedLanguage.values()) {
            Path dirPath = Paths.get(basePath.toString(), String.format("%s.lproj", language.glyphsTag()));
            Files.createDirectories(dirPath);
            Path filePath = Paths.get(dirPath.toString(), "IBdialog.strings");
            PrintWriter out = new PrintWriter(filePath.toFile());
            if(translationPackage.getLabels() == null) {
                return;
            }
            for(AdvancedHatchItemLabel label : translationPackage.getLabels()) {
                String formattedLabel = IBDialogFormat.format(label, language.getLocale());
                out.print(formattedLabel);
            }
            out.close();
        }
        System.out.printf("basePath %s", basePath);
    }

}
