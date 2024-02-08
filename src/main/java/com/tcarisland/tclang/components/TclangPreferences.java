package com.tcarisland.tclang.components;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
public class TclangPreferences {

    @Value("${output.path}")
    private String outputPath;

    @Value("${translation.service.uri}")
    private String translationServiceUrl;
}
