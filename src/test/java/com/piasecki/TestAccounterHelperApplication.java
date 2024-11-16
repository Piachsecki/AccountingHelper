package com.piasecki;

import org.springframework.boot.SpringApplication;

public class TestAccounterHelperApplication {

	public static void main(String[] args) {
		SpringApplication.from(AccounterHelperApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
