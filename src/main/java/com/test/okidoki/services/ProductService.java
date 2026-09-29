package com.test.okidoki.services;

import com.test.okidoki.entities.Product;
import com.test.okidoki.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product findByPrice(Integer price) {
        return productRepository.findByPrice(price);
    }
}
