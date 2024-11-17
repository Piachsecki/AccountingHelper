package com.piasecki;

import com.piasecki.domain.User;
import com.piasecki.service.UserService;
import com.piasecki.serviceImpl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountingHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingHelperApplication.class, args);
	}

}
