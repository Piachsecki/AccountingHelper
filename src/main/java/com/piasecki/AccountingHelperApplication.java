package com.piasecki;

import com.mindee.MindeeClient;
import com.mindee.input.LocalInputSource;
import com.mindee.parsing.common.AsyncPredictResponse;
import com.mindee.parsing.common.PredictResponse;
import com.mindee.parsing.standard.CompanyRegistrationField;
import com.mindee.parsing.standard.TaxField;
import com.mindee.product.invoice.InvoiceV4;
import com.mindee.product.receipt.ReceiptV5;
import com.piasecki.domain.User;
import com.piasecki.service.UserService;
import com.piasecki.serviceImpl.UserServiceImpl;
import org.mapstruct.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
//@ComponentScan(basePackages = "com.piasecki.mapper") // Include the mapper package
public class AccountingHelperApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(AccountingHelperApplication.class, args);
	}

}
