package com.onebox.usecases.cart;
import com.onebox.dataproviders.CartRepository;
import com.onebox.utils.StatusCode;
import org.quartz.Job;

public class GetCartByIdUseCaseImpl implements GetCartByIdUseCase {
    private final CartRepository cartRepository;

    public GetCartByIdUseCaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public GetCartByIdUseCaseResult execute(GetCartByIdUseCaseParams params) {
        GetCartByIdUseCaseResult result = GetCartByIdUseCaseResult.builder().build();
        result.setCart(cartRepository.findById(params.getCartId()));
        result.wasSuccessful();
        result.setStatusCode(StatusCode.SUCCESS);
        return result;
    }
}
