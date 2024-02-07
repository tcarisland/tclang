package com.tcarisland.tclang;

import lombok.Data;
import org.springframework.cglib.core.Local;

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
    private Map<String, String> translations;
}
