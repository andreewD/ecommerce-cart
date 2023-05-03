package com.onebox.usecases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Cart;
import com.onebox.entities.Product;
import com.onebox.utils.StatusCode;

import java.util.Objects;

public class DeleteProductFromCartUseCaseImpl implements DeleteProductFromCartUseCase{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public DeleteProductFromCartUseCaseImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DeleteProductFromCartUseCaseResult execute(DeleteProductFromCartUseCaseParams params) {
        DeleteProductFromCartUseCaseResult result = new DeleteProductFromCartUseCaseResult();

        if(params.getQuantity()<=0) {
            result.setStatusCode(StatusCode.INVALID_QUANTITY);
            return result;
        }

        Cart cart = cartRepository.findById(params.getCartId());

        if(Objects.isNull(cart)) {
            result.setStatusCode(StatusCode.CART_NOT_FOUND);
            return result;
        }

        if(cart.getProducts().isEmpty()) {
            result.setStatusCode(StatusCode.EMPTY_CART);
            return result;
        }

        if(!cartRepository.isProductInCart(params.getCartId(), params.getProductId(), params.getQuantity())) {
            result.setStatusCode(StatusCode.PRODUCT_NOT_FOUND_IN_CART);
            return result;
        }

        Product product = productRepository.findById(params.getProductId());

        if(Objects.isNull(product)) {
            result.setStatusCode(StatusCode.PRODUCT_NOT_FOUND);
            return result;
        }

        if(product.getStock() < params.getQuantity()) {
            result.setStatusCode(StatusCode.PRODUCT_STOCK_NOT_ENOUGH);
            return result;
        }

        productRepository.updateStock(product.getProduct_seq(), product.getStock() + params.getQuantity());
        cartRepository.deleteProductFromCart(params.getCartId(), params.getProductId(), params.getQuantity());

        result.setCart(cartRepository.findById(params.getCartId()));
        result.setStatusCode(StatusCode.SUCCESS);
        result.wasSuccessful();
        return result;
    }
}
