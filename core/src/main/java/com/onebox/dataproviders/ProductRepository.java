package com.onebox.dataproviders;

import com.onebox.entities.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

}
