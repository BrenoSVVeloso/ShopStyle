package com.microservice.bffshop.client;

import javax.validation.Valid;

import com.microservice.bffshop.dto.customer.UserDTO;
import com.microservice.bffshop.dto.customer.UserFormDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("/v1/users/:{id}")
    UserDTO getUser(@PathVariable int id);

    @PostMapping("/v1/users")
    void saveUser(@Valid @RequestBody UserFormDTO body);

    @PutMapping("/v1/users/:{id}")
    UserDTO updateUser(@PathVariable int id, @Valid @RequestBody UserFormDTO body);

    @GetMapping("/v1/users/email/:{id}")
    UserDTO findByEmail(@PathVariable String id);

}
