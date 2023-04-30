package com.onebox.usecases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.utils.StatusCode;

public class DeleteCartUseCaseImpl implements DeleteCartUseCase{
    private final CartRepository cartRepository;

    public DeleteCartUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public DeleteCartUseCaseResult execute(DeleteCartUseCaseParams params) {
        DeleteCartUseCaseResult result = DeleteCartUseCaseResult.builder().build();
        cartRepository.deleteCart(params.getCartId());
        result.setCartId(params.getCartId());
        result.wasSuccessful();
        result.setStatusCode(StatusCode.SUCCESS);
        return result;
    }
}
