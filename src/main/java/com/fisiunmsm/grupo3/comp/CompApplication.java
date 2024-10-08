package com.fisiunmsm.grupo3.comp;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompApplication {

	public static void main(String[] args) {

		//Locale.setDefault(Locale.forLanguageTag("ES"));

		SpringApplication.run(CompApplication.class, args);
	}

}
