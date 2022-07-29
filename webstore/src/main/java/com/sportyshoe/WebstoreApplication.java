package com.sportyshoe;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.sportyshoe")
@EntityScan(basePackages = "com.sportyshoe.bean")
public class WebstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebstoreApplication.class, args);
		Date currentDate = new Date();
		System.out.println("Spring Server Running at : " + currentDate.toString());
	}

}
