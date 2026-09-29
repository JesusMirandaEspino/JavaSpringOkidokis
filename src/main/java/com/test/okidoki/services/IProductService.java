package com.test.okidoki.services;

import com.test.okidoki.entities.Product;

public interface IProductService {
    Product findBySku(String sku);
    Product findByName(String name);
    Product findByPrice(Integer price);
}
