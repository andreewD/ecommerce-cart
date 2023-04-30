package com.onebox.repository.product;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl extends Product implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

}
