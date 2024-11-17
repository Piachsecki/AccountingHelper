package com.piasecki;

import com.mindee.MindeeClient;
import com.mindee.input.LocalInputSource;
import com.mindee.parsing.common.PredictResponse;
import com.mindee.parsing.standard.CompanyRegistrationField;
import com.mindee.parsing.standard.TaxField;
import com.mindee.product.receipt.ReceiptV5;
import com.piasecki.domain.User;
import com.piasecki.service.UserService;
import com.piasecki.serviceImpl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class AccountingHelperApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AccountingHelperApplication.class, args);
//
//		String apiKey = "bcb1045014fd0a1fa01e4a1c71a50c5f";
//		String filePath = "C:\\Users\\krpia\\Downloads\\AccountingHelper\\src\\main\\resources\\static\\PL_Receipt.jpg";
//
//		// Init a new client
//		MindeeClient mindeeClient = new MindeeClient(apiKey);
//
//		// Load a file from disk
//		LocalInputSource inputSource = new LocalInputSource(filePath);
//
//		// Parse the file
//		PredictResponse<ReceiptV5> response = mindeeClient.parse(
//				ReceiptV5.class,
//				inputSource
//		);
//
//
//		// pola potrzebne do price
//		System.out.println(response.getDocument().getInference().getPrediction().getTotalAmount().getValue());
//		System.out.println(response.getDocument().getInference().getPrediction().getLocale().getCurrency());
//		System.out.println();
//		System.out.println();
//		System.out.println();
//
//
//		//pola potrzebne do Company
//		System.out.println(response.getDocument().getInference().getPrediction().getSupplierName().getValue());
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		List<CompanyRegistrationField> supplierCompanyRegistrations = response.getDocument().getInference().getPrediction().getSupplierCompanyRegistrations();
//		for (CompanyRegistrationField supplierCompanyRegistration : supplierCompanyRegistrations) {
//			System.out.println(supplierCompanyRegistration.getValue());
//
//		}
//
//
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		//pola potrzebne do Address
//		System.out.println(response.getDocument().getInference().getPrediction().getSupplierAddress().getValue());


		// Print a summary of the response
//		System.out.println(response.toString());
	}

}
