package com.piasecki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
//@ComponentScan(basePackages = "com.piasecki.mapper") // Include the mapper package
public class AccountingHelperApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(AccountingHelperApplication.class, args);
	}

}
