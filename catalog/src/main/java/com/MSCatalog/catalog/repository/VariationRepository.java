package com.MSCatalog.catalog.repository;

import java.util.Optional;

import com.MSCatalog.catalog.entity.Variation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface VariationRepository extends MongoRepository<Variation, Integer>{

    @Query("{'product_id' :?0}")
    Optional<Variation> findBy_Product_id(int id);


}
