package com.codingplayground.propertyrental;

import com.codingplayground.propertyrental.auth.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ConfigProperties.class})
public class PropertyrentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyrentalApplication.class, args);
	}

}
