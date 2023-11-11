package br.com.startrip.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StUsuariosApplication {

	public static void main(String... args) {
		SpringApplication.run(StUsuariosApplication.class, args);
	}

}
