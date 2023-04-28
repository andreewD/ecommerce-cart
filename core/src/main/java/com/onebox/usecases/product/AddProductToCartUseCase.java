package com.onebox.usecases.product;

import com.onebox.entities.Product;

import java.util.List;

public interface AddProductToCartUseCase {
    List<Product> execute(Integer cartId, Product product);
}
