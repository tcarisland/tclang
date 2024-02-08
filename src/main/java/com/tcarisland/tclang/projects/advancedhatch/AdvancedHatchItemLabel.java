package com.tcarisland.tclang.projects.advancedhatch;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.util.Locale;
import java.util.Map;

@Data
public class AdvancedHatchItemLabel {
    /*
      - name: offsetPathStartTextField
    destination: cba-EJ-vKn
    translations:
      - en: End Stroke
     */
    private String name;
    private String destination;
    private Map<Locale, String> translations;
}
