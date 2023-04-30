package com.onebox.usecases.product;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import com.onebox.utils.StatusCode;

import java.util.List;

public class ListProductsUseCaseImpl implements ListProductsUseCase {
    private final ProductRepository productRepository;

    public ListProductsUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ListProductsUseCaseResult execute() {
        ListProductsUseCaseResult result = new ListProductsUseCaseResult();
        List<Product> products = productRepository.findAll();

        result.setProducts(products);
        result.setStatusCode(StatusCode.SUCCESS);
        result.wasSuccessful();
        return result;
    }
}
