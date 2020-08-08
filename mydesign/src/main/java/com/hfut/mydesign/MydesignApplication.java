package com.hfut.mydesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MydesignApplication {
    public static void main(String[] args) {
        SpringApplication.run(MydesignApplication.class, args);
    }
}
