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

    public Long save(Product product) {
        entityManager.createQuery("INSERT INTO Product (id,name,description,price) VALUES (?,?,?,?)", Product.class).setParameter(1, product.getId()).setParameter(2, product.getName()).setParameter(3, product.getDescription()).setParameter(4, product.getPrice());
        return product.getId();
    }



}
