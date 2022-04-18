package com.bffshop.bffshop.client;

import com.bffshop.bffshop.dto.customer.UserDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer")
public interface CustomerClient {

    @GetMapping("/v1/users/:{id}")
    UserDTO getUser(@PathVariable int id);

}
