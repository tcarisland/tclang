package com.tcarisland.tclang.components;

import com.tcarisland.tclang.projects.advancedhatch.AdvancedHatchAutoTranslator;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Component
public class LangParser {

    @Autowired
    AdvancedHatchAutoTranslator advancedHatchAutoTranslator;

    public void run() {
        advancedHatchAutoTranslator.run();
    }
}
