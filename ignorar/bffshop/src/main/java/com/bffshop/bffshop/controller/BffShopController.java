package com.bffshop.bffshop.controller;


import com.bffshop.bffshop.dto.customer.UserDTO;
import com.bffshop.bffshop.service.BffShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BffShopController {

    @Autowired
    private BffShopService bffShopService;
    
    @PostMapping("/users")
    public ResponseEntity<?> saveUsers(){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> saveLogin(){
        return null;
    }

    @GetMapping("/users/:{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
        UserDTO userDTO = this.bffShopService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/users/:{id}")
    public ResponseEntity<?> uptadeUser(@PathVariable int id){
        return null;
    }

    @GetMapping("/products/:{id}")
    public ResponseEntity<?> getProducts(@PathVariable int id){
        return null;
    }

    @GetMapping("/categories/:{id}/products")
    public ResponseEntity<?> getCategoriesProducts(@PathVariable int id){
        return null;
    }

    @GetMapping("/payments")
    public ResponseEntity<?> getPayments(){
        return null;
    }

    @PostMapping("/purchases")
    public ResponseEntity<?> savePurchases(){
        return null;
    }
    
    @GetMapping("/historic/user/:{idUser}")
    public ResponseEntity<?> getHistoricUser(){
        return null;
    }
}
