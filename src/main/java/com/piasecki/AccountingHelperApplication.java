package com.piasecki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = "com.piasecki.mapper") // Include the mapper package
public class AccountingHelperApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(AccountingHelperApplication.class, args);
	}

}
