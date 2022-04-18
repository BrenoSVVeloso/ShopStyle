package com.history.history.repository;

import java.util.Optional;

import com.history.history.entity.History;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History,Integer>{

    @Query("{id : ?0}")
    History findHistoryByPurchasesId(int id);

    Optional<History> findHistoryByUserId(int id);

}
