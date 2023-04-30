package com.onebox.usecases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.utils.StatusCode;

public class ListCartsUseCaseImpl implements ListCartsUseCase{
    private final CartRepository cartRepository;

    public ListCartsUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    @Override
    public ListCartsUseCaseResult execute() {
        ListCartsUseCaseResult result = ListCartsUseCaseResult.builder().build();
        result.setCarts(cartRepository.findAll());
        result.wasSuccessful();
        result.setStatusCode(StatusCode.SUCCESS);
        return result;
    }
}
