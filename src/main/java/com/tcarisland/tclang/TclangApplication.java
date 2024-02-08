package com.tcarisland.tclang;

import com.tcarisland.tclang.components.LangMenuRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EntityScan(basePackages = "com.tcarisland.tclang")
public class TclangApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(TclangApplication.class, args);
		LangMenuRunner langParser = applicationContext.getBean(LangMenuRunner.class);
		langParser.run(Stream.of(args).collect(Collectors.toSet()));
	}

}
