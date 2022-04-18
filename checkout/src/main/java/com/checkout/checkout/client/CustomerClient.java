package com.checkout.checkout.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dto.customer.UserDTO;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("/v1/users/:{id}")
    UserDTO getUser(@PathVariable int id);
}
