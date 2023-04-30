package com.onebox.repository.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.entities.Cart;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class CartDaoImpl extends Cart implements CartRepository {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public List<Cart> findAll() {
        return entityManager.createQuery("SELECT c FROM Cart c", Cart.class).getResultList();
    }

    @Override
    public UUID addCart() {
        Cart cart = new Cart();
        cart.setProducts(new ArrayList<>());
        cart.setTotal(0.0);
        entityManager.persist(cart);
        return cart.getId();
    }

    @Override
    public Cart findById(UUID id) {
        return entityManager.find(Cart.class, id);
    }

    @Override
    public void deleteCart(UUID id) {
        Cart cart = entityManager.find(Cart.class, id);
        entityManager.remove(cart);
    }

}
