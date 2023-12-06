package com.example.PreEntrega;

import com.example.PreEntrega.repository.ClientRepository;
import com.example.PreEntrega.repository.InvoiceRepository;
import com.example.PreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class PreEntregaApplication {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(PreEntregaApplication.class, args);
	}

}
