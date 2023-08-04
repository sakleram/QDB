package com.example.docmanagemntapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class DocmanagemntApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocmanagemntApiApplication.class, args);
	}

}
