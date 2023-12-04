package com.example.clientService;

import com.example.clientService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
class ClientServiceApplication {
	@Autowired
	ClientService clientService;

	public static void main(String[] args){
		SpringApplication.run(ClientServiceApplication.class, args);
	}


}
