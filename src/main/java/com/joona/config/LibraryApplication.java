package com.joona.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.joona")
public class LibraryApplication {
    public static void main(String[] args) {
        System.out.println("Starting the application");
        SpringApplication.run(LibraryApplication.class,args);
        System.out.println("Application started successfully");
    }
}
