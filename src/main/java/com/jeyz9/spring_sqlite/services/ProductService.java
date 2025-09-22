package com.jeyz9.spring_sqlite.services;

import com.jeyz9.spring_sqlite.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer id);
    Product createProduct(Product product);
    Optional<Product> updateProduct(Integer id, Product productDetails);
    boolean deleteProduct(Integer id);
}
