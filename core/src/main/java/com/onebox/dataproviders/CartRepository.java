package com.onebox.dataproviders;

import com.onebox.entities.Cart;
import jdk.jfr.Timestamp;


import java.util.List;
import java.util.UUID;

public interface CartRepository {
    List<Cart> findAll();
    UUID addCart();
    Cart findById(UUID id);
    void deleteCart(UUID id);
    void addProductToCart(UUID cartId, Integer productId, Integer quantity,Double price);
    List<Cart> findInactiveCarts();
    void deleteAll(List<Cart> inactiveCarts);
}
