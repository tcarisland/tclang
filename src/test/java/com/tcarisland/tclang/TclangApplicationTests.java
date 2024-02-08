package com.tcarisland.tclang;

import com.tcarisland.tclang.components.SupportedLanguage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class TclangApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void printLanguages() {
		for(SupportedLanguage lang : SupportedLanguage.values()) {
			Locale l = lang.getLocale();
			System.out.printf("LANG %s %n", lang.glyphsTag());
		}
	}

}
