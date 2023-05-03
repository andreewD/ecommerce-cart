package com.onebox.usescases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.entities.Cart;
import com.onebox.usecases.cart.AddCartUseCaseImpl;
import com.onebox.usecases.cart.AddCartUseCaseResult;
import com.onebox.usecases.cart.AddProductToCartUseCaseResult;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AddCartUseCaseTest {

    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private final AddCartUseCaseImpl addCartUseCase = new AddCartUseCaseImpl(cartRepository);
    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testAddCart() {
//        AddCartUseCaseResult addCartUseCaseResult = AddCartUseCaseResult
//                .builder()
//                .cartId(UUID.randomUUID())
//                .build();
//
//        when(addCartUseCase.execute()).thenReturn(addCartUseCaseResult);
//
//        AddCartUseCaseResult result =  addCartUseCase.execute();
//
//        // Verify that the result is not null
//        assertTrue(result != null);
//
//        // Verify data
//        assertTrue(result.getCartId() instanceof UUID);
    }
}
