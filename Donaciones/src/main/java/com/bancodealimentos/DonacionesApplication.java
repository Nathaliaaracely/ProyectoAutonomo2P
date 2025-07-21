package com.bancodealimentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bancodealimentos")
public class DonacionesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DonacionesApplication.class, args);
    }
}
