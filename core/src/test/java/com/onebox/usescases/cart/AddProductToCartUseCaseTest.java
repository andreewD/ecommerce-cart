package com.onebox.usescases.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Cart;
import com.onebox.entities.Product;
import com.onebox.usecases.cart.AddProductToCartUseCaseImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddProductToCartUseCaseTest {

    private final CartRepository cartRepository = mock(CartRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final AddProductToCartUseCaseImpl addProductToCartUseCase = new AddProductToCartUseCaseImpl(cartRepository,productRepository);

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testAddProductToCart() {
        Product product = Product.builder()
                .product_seq(1)
                .name("Product 1")
                .description("Product 1")
                .stock(10)
                .price(10.0)
                .build();

        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        Cart cart = Cart.builder()
                .id(uuid)
                .total(0.0)
                .createdAt(date)
                .updatedAt(date)
                .build();

        when(productRepository.create(product)).thenReturn(product);

        Product productCreated = productRepository.create(product);

        // Verify that the result is not null
        assertTrue(productCreated != null);

        // Verify data
        assertTrue(productCreated.getProduct_seq() == 1);
        assertTrue(productCreated.getName().equals("Product 1"));
        assertTrue(productCreated.getDescription().equals("Product 1"));
        assertTrue(productCreated.getStock() == 10);
        assertTrue(productCreated.getPrice() == 10.0);

        // Add product to cart

    }
}
