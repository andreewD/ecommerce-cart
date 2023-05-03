package com.onebox.usecases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import com.onebox.utils.StatusCode;

import java.util.Objects;

public class AddProductToCartUseCaseImpl implements AddProductToCartUseCase{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartUseCaseImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public AddProductToCartUseCaseResult execute(AddProductToCartUseCaseParams params) {
        AddProductToCartUseCaseResult result = AddProductToCartUseCaseResult.builder().build();

        if(params.getQuantity()<=0) {
            result.setStatusCode(StatusCode.INVALID_QUANTITY);
            return result;
        }

        Product product = productRepository.findById(params.getProductId());

        if(params.getQuantity()<=0) {
            result.setStatusCode(StatusCode.INVALID_QUANTITY);
            return result;
        }

        if (Objects.isNull(product)) {
            result.setStatusCode(StatusCode.PRODUCT_NOT_FOUND);
            return result;
        }

        if(product.getStock() < params.getQuantity()) {
            result.setStatusCode(StatusCode.PRODUCT_STOCK_NOT_ENOUGH);
            return result;
        }

        productRepository.updateStock(product.getProduct_seq(), product.getStock() - params.getQuantity());
        cartRepository.addProductToCart(params.getCartId(), params.getProductId(), params.getQuantity(), product.getPrice() * params.getQuantity());
        result.setCart(cartRepository.findById(params.getCartId()));
        result.setStatusCode(StatusCode.SUCCESS);
        result.wasSuccessful();
        return result;
    }
}
