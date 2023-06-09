package com.onebox.configuration;

import com.onebox.dataproviders.CartRepository;
import com.onebox.dataproviders.ProductRepository;
import com.onebox.ecommerce.entrypoints.converter.DTOToDomainConverter;
import com.onebox.ecommerce.entrypoints.converter.DomainToDTOConverter;
import com.onebox.repository.cart.CartDaoImpl;
import com.onebox.repository.product.ProductDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new ProductDaoImpl();
    }

    @Bean
    public CartRepository cartRepository(){
        return new CartDaoImpl();
    }

    @Bean
    public DomainToDTOConverter domainToDTOConverter() {
        return new DomainToDTOConverter();
    }

    @Bean
    public DTOToDomainConverter dtoToDomainConverter() {
        return new DTOToDomainConverter();
    }

}
