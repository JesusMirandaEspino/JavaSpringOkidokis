package com.test.okidoki.repositories;

import com.test.okidoki.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findBySku(String sku);
    public Product findByName(String name);
    public Product findByPrice(Integer price);
}
