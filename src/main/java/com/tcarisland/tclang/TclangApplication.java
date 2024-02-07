package com.tcarisland.tclang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class TclangApplication {

	public static void main(String[] args) {
		SpringApplication.run(TclangApplication.class, args);
	}

	public TclangApplication() {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            AdvancedHatchTranslationPackage advancedHatchTranslationPackage = mapper
					.readValue(
							new File("src/main/resources/projects/advancedhatch/advancedhatch.yml"),
							AdvancedHatchTranslationPackage.class
					);
			advancedHatchTranslationPackage.getLabels().forEach(u -> {
				System.out.printf("%s %s %n", u.getDestination(), u.getName());
			});
        } catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
        }
    }

}
