package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = H2ConsoleAutoConfiguration.class)
public class TwoDataBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoDataBaseApplication.class, args);
    }
     @GetMapping("/test")
    public String getOrdersone(){
        return  "This is Example file are one";
    }
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
