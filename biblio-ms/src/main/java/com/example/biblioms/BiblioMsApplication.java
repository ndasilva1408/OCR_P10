package com.example.biblioms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BiblioMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiblioMsApplication.class, args);
    }

}
