package com.onebox.service;

import com.onebox.dataproviders.CartRepository;
import com.onebox.entities.Cart;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class CartCleanupService {
    @Autowired
    private final CartRepository cartRepository;

    public CartCleanupService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void deleteInactiveCarts() {
        List<Cart> inactiveCarts = cartRepository.findInactiveCarts();
        if (inactiveCarts.size() > 0) {
            log.info("Found {} inactive carts", inactiveCarts.size());
            cartRepository.deleteAll(inactiveCarts);
            log.info("Deleted {} inactive carts", inactiveCarts.size());
        }
    }
}
