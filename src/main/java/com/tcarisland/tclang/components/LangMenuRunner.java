package com.tcarisland.tclang.components;

import com.tcarisland.tclang.projects.advancedhatch.AdvancedHatchAutoTranslator;
import com.tcarisland.tclang.projects.advancedhatch.AdvancedHatchFilePrinter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Component
public class LangMenuRunner {

    @Autowired
    AdvancedHatchAutoTranslator advancedHatchAutoTranslator;
    @Autowired
    AdvancedHatchFilePrinter advancedHatchFilePrinter;

    public void run(Set<String> args) {
        if(args.contains("AutoTranslate")) {
            System.out.println("AutoTranslate Selected");
            advancedHatchAutoTranslator.run();
        }
        if(args.contains("PrintTranslations")) {
            System.out.println("PrintTranslations Selected");
            if(args.contains("AdvancedHatch")) {
                System.out.println("AdvancedHatch Selected");
                advancedHatchFilePrinter.run();
            }
        }
    }
}
