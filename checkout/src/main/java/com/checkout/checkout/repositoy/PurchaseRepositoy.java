package com.checkout.checkout.repositoy;

import com.checkout.checkout.entity.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import dto.PurchaseDTO;

public interface PurchaseRepositoy extends JpaRepository<Purchase, Integer>{

    void save(PurchaseDTO purchaseDTO);
    
}
