package com.MSCatalog.catalog.repository;

import com.MSCatalog.catalog.entity.Category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, Integer>{
    
}
