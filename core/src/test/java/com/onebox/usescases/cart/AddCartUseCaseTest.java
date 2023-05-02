package com.onebox.usescases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.usecases.cart.AddCartUseCaseImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class AddCartUseCaseTest {
    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private final AddCartUseCaseImpl addCartUseCase = new AddCartUseCaseImpl(cartRepository);

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testAddCart() {

    }
}
