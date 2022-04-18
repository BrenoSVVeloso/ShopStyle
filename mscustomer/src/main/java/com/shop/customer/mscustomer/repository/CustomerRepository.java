package com.shop.customer.mscustomer.repository;

import java.util.Optional;

import com.shop.customer.mscustomer.entity.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Usuario, Integer>{

    Optional<Usuario> findByEmail(String email);
    
}
