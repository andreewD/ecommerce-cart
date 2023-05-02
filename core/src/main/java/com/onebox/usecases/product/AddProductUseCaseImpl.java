package com.onebox.usecases.product;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import com.onebox.utils.StatusCode;

public class AddProductUseCaseImpl implements AddProductUseCase{
    private final ProductRepository productRepository;

    public AddProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public AddProductUseCaseResult execute(AddProductUseCaseParams params) {
        AddProductUseCaseResult result = AddProductUseCaseResult.builder().build();
        Product product = params.getProduct();
        productRepository.create(product);
        result.setProduct(product);
        result.setStatusCode(StatusCode.SUCCESS);
        result.wasSuccessful();
        return result;
    }
}
