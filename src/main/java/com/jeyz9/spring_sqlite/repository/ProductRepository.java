package com.jeyz9.spring_sqlite.repository;

import com.jeyz9.spring_sqlite.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    int save(Product product);
    int update(Product product);
    int deleteById(Integer id);
}
