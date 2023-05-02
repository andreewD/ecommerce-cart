package com.onebox.usecases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

public class AddCartUseCaseImpl implements AddCartUseCase{
    private final CartRepository cartRepository;


    public AddCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public AddCartUseCaseResult execute() {
        AddCartUseCaseResult result = AddCartUseCaseResult.builder().build();
        result.setCartId(cartRepository.addCart());

        result.wasSuccessful();
        result.setStatusCode(StatusCode.SUCCESS);
        return result;
    }
}
