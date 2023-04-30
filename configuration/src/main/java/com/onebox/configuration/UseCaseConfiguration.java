package com.onebox.configuration;

import com.onebox.dataproviders.CartRepository;
import com.onebox.dataproviders.ProductRepository;
import com.onebox.usecases.cart.AddCartUseCaseImpl;
import com.onebox.usecases.cart.AddProductToCartUseCaseImpl;
import com.onebox.usecases.cart.DeleteCartUseCaseImpl;
import com.onebox.usecases.cart.GetCartByIdUseCaseImpl;
import com.onebox.usecases.cart.ListCartsUseCaseImpl;
import com.onebox.usecases.product.ListProductsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    @Bean
    public GetCartByIdUseCaseImpl getCartByIdUseCase(CartRepository cartRepository) {
        return new GetCartByIdUseCaseImpl(cartRepository);
    }

    @Bean
    public ListCartsUseCaseImpl listCartsUseCase(CartRepository cartRepository) {
        return new ListCartsUseCaseImpl(cartRepository);
    }

    @Bean
    public AddCartUseCaseImpl addCartUseCase(CartRepository cartRepository) {
        return new AddCartUseCaseImpl(cartRepository);
    }

    @Bean
    public DeleteCartUseCaseImpl deleteCartUseCase(CartRepository cartRepository) {
        return new DeleteCartUseCaseImpl(cartRepository);
    }

    @Bean
    public ListProductsUseCaseImpl listProductsUseCase(ProductRepository productRepository) {
        return new ListProductsUseCaseImpl(productRepository);
    }

    @Bean
    AddProductToCartUseCaseImpl addProductToCartUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        return new AddProductToCartUseCaseImpl(cartRepository, productRepository);
    }

}
