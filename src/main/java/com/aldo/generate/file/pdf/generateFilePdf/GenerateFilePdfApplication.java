package com.aldo.generate.file.pdf.generateFilePdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class  GenerateFilePdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenerateFilePdfApplication.class, args);
	}

}
