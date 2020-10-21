package com.demo.propertyreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class PropertyreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyreaderApplication.class, args);
	}

}
