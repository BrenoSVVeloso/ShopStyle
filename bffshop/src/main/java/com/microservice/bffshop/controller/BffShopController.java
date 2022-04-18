package com.microservice.bffshop.controller;


import java.util.List;

import javax.validation.Valid;

import com.microservice.bffshop.dto.catalog.ProductDTO;
import com.microservice.bffshop.dto.checkout.PaymentDTO;
import com.microservice.bffshop.dto.checkout.PurchaseDTO;
import com.microservice.bffshop.dto.checkout.PurchaseFormDTO;
import com.microservice.bffshop.dto.customer.UserDTO;
import com.microservice.bffshop.dto.customer.UserFormDTO;
import com.microservice.bffshop.dto.history.HistoryDTO;
import com.microservice.bffshop.service.BffShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BffShopController {

    @Autowired
    private BffShopService bffShopService;
    
    @PostMapping("/users")
    @Transactional
    public ResponseEntity<?> saveUsers(@Valid @RequestBody UserFormDTO body){
        this.bffShopService.saveUser(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> saveLogin(){
        return null;
    }

    @GetMapping("/users/:{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
        UserDTO userDTO = this.bffShopService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/users/:{id}")
    @Transactional
    public ResponseEntity<UserDTO> uptadeUser(@PathVariable int id, @Valid @RequestBody UserFormDTO body){
        UserDTO userDTO = this.bffShopService.uptadeUser(id, body);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/products/:{id}")
    public ResponseEntity<ProductDTO> getProducts(@PathVariable int id){
        ProductDTO productDTO = this.bffShopService.getProduct(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/categories/:{id}/products")
    public ResponseEntity<?> getCategoriesProducts(@PathVariable int id){
        return null;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentDTO>> getPayments(){
        List<PaymentDTO> paymentDTOs = this.bffShopService.getPaymets();
        return ResponseEntity.ok(paymentDTOs);
    }

    @PostMapping("/purchases")
    @Transactional
    public ResponseEntity<PurchaseDTO> savePurchases(@Valid @RequestBody PurchaseFormDTO body){
        this.bffShopService.savePurchases(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/historic/user/:{id}")
    public ResponseEntity<HistoryDTO> getHistoricUser(@PathVariable int id){
        HistoryDTO historyDTO = this.bffShopService.getHistory(id);
        return ResponseEntity.ok(historyDTO);
    }
}
