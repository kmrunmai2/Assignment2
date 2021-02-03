package com.example;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.web.client.RestClientException;

import com.example.controller.CarDetailsShareController;

@SpringBootApplication
public class SpringbootApp2Application {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx=SpringApplication.run(SpringbootApp2Application.class, args);
		CarDetailsShareController cdsc=ctx.getBean(CarDetailsShareController.class);
		cdsc.getCardetails();
	}
	
	

}
