package com.MSCatalog.catalog.repository;

import com.MSCatalog.catalog.entity.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer>{
    
}
