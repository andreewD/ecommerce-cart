package com.onebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Main {
    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/v1");
        SpringApplication.run(Main.class, args);
    }

}