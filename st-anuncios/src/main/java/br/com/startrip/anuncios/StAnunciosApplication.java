package br.com.startrip.anuncios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StAnunciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(StAnunciosApplication.class, args);
	}

}
