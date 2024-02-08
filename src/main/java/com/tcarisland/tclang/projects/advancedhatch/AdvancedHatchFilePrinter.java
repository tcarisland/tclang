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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
