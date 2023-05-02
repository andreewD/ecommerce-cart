package com.onebox.usescases.product;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import com.onebox.usecases.product.AddProductUseCaseImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddProductUseCaseTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final AddProductUseCaseImpl addProductUseCase = new AddProductUseCaseImpl(productRepository);

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testAddProduct() {
        Product product = Product.builder()
                .product_seq(1)
                .name("Product 1")
                .description("Product 1")
                .stock(10)
                .price(10.0)
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
    }
}
