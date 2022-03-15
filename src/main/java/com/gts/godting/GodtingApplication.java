package com.gts.godting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GodtingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodtingApplication.class, args);
	}

}
