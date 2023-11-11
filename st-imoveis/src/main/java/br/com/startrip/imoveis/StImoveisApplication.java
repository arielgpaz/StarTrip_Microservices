package br.com.startrip.imoveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StImoveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(StImoveisApplication.class, args);
	}

}
