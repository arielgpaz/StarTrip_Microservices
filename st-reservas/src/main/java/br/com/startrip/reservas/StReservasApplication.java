package br.com.startrip.reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StReservasApplication {

    public static void main(String[] args) {
        SpringApplication.run(StReservasApplication.class, args);
    }

}
