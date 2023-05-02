package com.onebox.repository.cart;

import com.onebox.dataproviders.CartRepository;
import com.onebox.entities.Cart;
import com.onebox.entities.CartItem;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
@Slf4j
public class CartDaoImpl extends Cart implements CartRepository {
    @Value("${cart.inactive.time}")
    private Long cartInactiveTime;

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public List<Cart> findAll() {
        return entityManager.createQuery("SELECT c FROM Cart c", Cart.class).getResultList();
    }

    @Override
    public UUID addCart() {
        Cart cart = new Cart();
        cart.setProducts("");
        cart.setTotal(0.0);
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());
        entityManager.persist(cart);
        return cart.getId();
    }

    @Override
    public Cart findById(UUID id) {
        return entityManager.find(Cart.class, id);
    }

    @Override
    public void deleteCart(UUID id) {
        Cart cart = findById(id);
        entityManager.remove(cart);
    }

    @Override
    public void addProductToCart(UUID cartId, Integer productId, Integer quantity, Double price) {
        Cart cart = entityManager.find(Cart.class, cartId);

        //Convert products string to List of CartItem
        List<CartItem> products = new ArrayList<>();
        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
            String[] productsData = cart.getProducts().split(",");
            for (String productData : productsData) {
                String[] productDataArray = productData.split(":");
                CartItem item = new CartItem();
                item.setProductId(Integer.parseInt(productDataArray[0]));
                item.setQuantity(Integer.parseInt(productDataArray[1]));
                item.setPrice(Double.parseDouble(productDataArray[2]));
                products.add(item);
            }
        }

        // Check if product is already in cart
        Optional<CartItem> itemOptional = products.stream().filter(item -> item.getProductId().equals(productId)).findFirst();

        if (itemOptional.isPresent()) {
            CartItem item = itemOptional.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setPrice(item.getPrice() + (price));

            products.set(products.indexOf(item), item);
        } else {
            CartItem item = new CartItem(productId, quantity,  price);
            products.add(item);
        }

        //Convert List of CartItem to products string
        List<String> productsString = new ArrayList<>();
        for (CartItem product : products) {
            productsString.add(product.getProductId() + ":" + product.getQuantity() + ":" + product.getPrice());
        }

        cart.setProducts(String.join(",", productsString));
        cart.setTotal(cart.getTotal() + (price));
        entityManager.merge(cart);
    }

    @Override
    public List<Cart> findInactiveCarts() {
        Long currentTime = System.currentTimeMillis();
        Long inactivityTime = currentTime - (1 * 60 * 1000);
        return entityManager.createQuery("SELECT c FROM Cart c WHERE c.updatedAt < :inactivityTime", Cart.class)
                .setParameter("inactivityTime", new Date(inactivityTime))
                .getResultList();
    }

    @Override
    public void deleteAll(List<Cart> inactiveCarts) {
        for (Cart cart : inactiveCarts) {
            entityManager.remove(cart);
        }
    }

}
