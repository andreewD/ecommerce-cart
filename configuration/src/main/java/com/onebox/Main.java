package com.onebox;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Main {
    @Autowired
    private ProductRepository productRepository;
    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/v1");
        SpringApplication.run(Main.class, args);
    }

}