package com.onebox.configuration;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.repository.product.ProductDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new ProductDaoImpl();
    }
}
