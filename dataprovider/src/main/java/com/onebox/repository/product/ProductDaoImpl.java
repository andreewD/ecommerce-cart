package com.onebox.repository.product;

import com.onebox.dataproviders.ProductRepository;
import com.onebox.entities.Product;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl extends Product implements ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public Product findById(Integer id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void updateStock(Integer id, Integer stock) {
        Product product = entityManager.find(Product.class, id);
        product.setStock(stock);
        entityManager.merge(product);
    }

    @Override
    public Product create(Product product) {
        entityManager.persist(product);
        return product;
    }

}
