package com.checkout.checkout.controller;

import java.util.List;

import javax.validation.Valid;

import com.checkout.checkout.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.PaymentDTO;
import dto.PaymentFormDTO;
import dto.PurchaseDTO;
import dto.PurchaseFormDTO;

@RestController
@RequestMapping("/v1")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    

    @PostMapping("/payments")
    @Transactional
    public ResponseEntity<?> savePayment(@Valid @RequestBody PaymentFormDTO body){
        
        checkoutService.savePayment(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> listPayments(){

        List<PaymentDTO> paymentDTOs = checkoutService.listPayments();
        return ResponseEntity.ok(paymentDTOs);
    }

    @GetMapping("/payments/:{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable int id){
        
        PaymentDTO paymentDTO = checkoutService.getPayment(id);
        return ResponseEntity.ok(paymentDTO);
    }

    @PutMapping("/payments/:{id}")
    @Transactional
    public ResponseEntity<PaymentDTO> uptadePayment(@Valid @RequestBody PaymentFormDTO body, @PathVariable int id){
        PaymentDTO paymentDTO = checkoutService.uptadePayment(body, id);
        return ResponseEntity.ok(paymentDTO);
    }

    @DeleteMapping("/payments/:{id}")
    @Transactional
    public ResponseEntity<?> deletePayment(@PathVariable int id){
        checkoutService.deletePayment(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/purchases")
    @Transactional
    public ResponseEntity<PurchaseDTO> purchase(@Valid @RequestBody PurchaseFormDTO body){
        PurchaseDTO purchaseDTO = checkoutService.purchase(body);
        return ResponseEntity.ok(purchaseDTO);
    }

    @GetMapping("/purchases/:{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseByUserId(@PathVariable int id){
        PurchaseDTO purchaseDTO = checkoutService.getPurchaseByUserId(id);
        return ResponseEntity.ok(purchaseDTO);
    }
}
