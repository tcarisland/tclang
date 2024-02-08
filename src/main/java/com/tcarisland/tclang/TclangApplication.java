package com.tcarisland.tclang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tcarisland.tclang.components.LangParser;
import com.tcarisland.tclang.projects.advancedhatch.AdvancedHatchTranslationPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@SpringBootApplication
@EntityScan(basePackages = "com.tcarisland.tclang")
public class TclangApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(TclangApplication.class, args);
		LangParser langParser = applicationContext.getBean(LangParser.class);
		langParser.run();
	}

}
