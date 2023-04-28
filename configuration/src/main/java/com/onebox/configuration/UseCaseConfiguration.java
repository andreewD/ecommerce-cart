package com.onebox.configuration;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.usecases.cart.GetCartByIdUseCaseImpl;
import com.onebox.usecases.product.ListProductsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public GetCartByIdUseCaseImpl getCartByIdUseCase() {
        return new GetCartByIdUseCaseImpl();
    }

    @Bean
    public ListProductsUseCaseImpl listProductsUseCase(ProductRepository productRepository) {
        return new ListProductsUseCaseImpl(productRepository);
    }

}
