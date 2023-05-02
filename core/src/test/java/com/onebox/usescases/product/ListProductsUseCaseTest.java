package com.onebox.usescases.product;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import com.onebox.usecases.product.ListProductsUseCaseImpl;
import com.onebox.usecases.product.ListProductsUseCaseResult;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ListProductsUseCaseTest {
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final ListProductsUseCaseImpl listProductsUseCase = new ListProductsUseCaseImpl(productRepository);

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void testEmptyListProducts() {
        ListProductsUseCaseResult result = listProductsUseCase.execute();
        // Verify that the result is not null
        assertTrue(result != null);
        // Verify the products list is empty
        assertTrue(result.getProducts().isEmpty());
        // Count the number of times that the method findAll() is called
        verify(productRepository, times(1)).findAll();
        // Verify that the result was successful
        assertTrue(result.wasSuccessful());

    }

    @Test
    public void testNonEmptyListProducts() {
        Product product = Product.builder()
                .product_seq(1)
                .name("Product 1")
                .description("Product 1")
                .stock(10)
                .price(10.0)
                .build();

        Product product2 = Product.builder()
                .product_seq(2)
                .name("Product 2")
                .description("Product 2")
                .stock(10)
                .price(10.0)
                .build();

        Mockito.when(productRepository.create(product)).thenReturn(product);
        Mockito.when(productRepository.create(product2)).thenReturn(product2);

        List<Product> products = Arrays.asList(product, product2);
        Mockito.when(productRepository.findAll()).thenReturn(products);

        ListProductsUseCaseResult result = listProductsUseCase.execute();

        // Verify that the result is not null
        assertTrue(result != null);
        // Verify the products list is not empty
        assertFalse(result.getProducts().isEmpty());
        // Verify that the result contains the expected products
        assertTrue(result.getProducts().contains(product));
        assertTrue(result.getProducts().contains(product2));
        // Count the number of times that the method findAll() is called
        verify(productRepository, times(1)).findAll();
        // Verify that the result was successful
        assertTrue(result.wasSuccessful());
    }

}
