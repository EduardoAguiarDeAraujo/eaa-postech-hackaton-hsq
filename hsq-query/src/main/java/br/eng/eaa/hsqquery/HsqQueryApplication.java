package br.eng.eaa.hsqquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Add this annotation
public class HsqQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsqQueryApplication.class, args);
    }

}
