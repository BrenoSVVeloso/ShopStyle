package com.microservice.bffshop.client;

import java.util.List;

import javax.validation.Valid;

import com.microservice.bffshop.dto.checkout.PaymentDTO;
import com.microservice.bffshop.dto.checkout.PurchaseFormDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("checkout")
public interface CheckoutClient {

    @GetMapping("/v1/payments")
    List<PaymentDTO> getPayments();

    @PostMapping("/v1/purchases")
    void savePurchases(@Valid @RequestBody PurchaseFormDTO body);
    
}
