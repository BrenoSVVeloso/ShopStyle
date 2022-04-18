package com.checkout.checkout.repositoy;

import com.checkout.checkout.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>{
    
}
