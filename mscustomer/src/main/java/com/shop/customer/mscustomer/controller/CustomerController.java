package com.shop.customer.mscustomer.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.shop.customer.mscustomer.dto.LoginFormDTO;
import com.shop.customer.mscustomer.dto.TokenDTO;
import com.shop.customer.mscustomer.dto.UsuarioDTO;
import com.shop.customer.mscustomer.dto.UsuarioFormDTO;
import com.shop.customer.mscustomer.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    //Autorização??
    @PostMapping("/login")
    @Transactional
    public ResponseEntity<TokenDTO> saveLogin(@Valid @RequestBody LoginFormDTO body){
        TokenDTO token = customerService.saveLogin(body);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<UsuarioDTO> saveUser(@Valid @RequestBody UsuarioFormDTO body){
        UsuarioDTO usuario = customerService.saveUser(body);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/users/:{id}")
    @Transactional
    public ResponseEntity<UsuarioDTO> getUser(@PathVariable int id){
        UsuarioDTO usuario = customerService.getUser(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/users/:{id}")
    @Transactional
    public ResponseEntity<UsuarioDTO> uptadeUser(@PathVariable int id, @Valid @RequestBody UsuarioFormDTO body){
        UsuarioDTO usuario = customerService.uptadeUser(id, body);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/users/email/:{id}")
    public ResponseEntity<UsuarioDTO> getUserByEmail(@PathVariable String id){
        UsuarioDTO usuario = customerService.getUserEmail(id);
        return ResponseEntity.ok(usuario);
    }

}
