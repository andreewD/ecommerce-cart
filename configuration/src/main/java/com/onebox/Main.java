package com.onebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@ComponentScan
@EnableScheduling
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/v1");
        SpringApplication.run(Main.class, args);
    }

}