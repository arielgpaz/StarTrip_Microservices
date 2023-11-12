package br.com.startrip.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StPagamentosApplication {

    public static void main(String[] args) {
        SpringApplication.run(StPagamentosApplication.class, args);
    }

}
