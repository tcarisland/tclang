package com.tcarisland.tclang.components;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Component
public class LangParser {

    @Autowired
    private TclangPreferences preferences;

    public void run() {
        System.out.printf("outputPath: %s%n", preferences.getOutputPath());
    }
}
