package com.cofeapp.cofeappgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CofeAppGwApplication {

    public static void main(String[] args) {
        SpringApplication.run(CofeAppGwApplication.class, args);
    }
}
