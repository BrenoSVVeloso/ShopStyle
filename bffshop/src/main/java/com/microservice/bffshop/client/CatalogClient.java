package com.microservice.bffshop.client;

import com.microservice.bffshop.dto.catalog.ProductDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("catalog")
public interface CatalogClient {

    @GetMapping("/v1/products/:{id}")
    ProductDTO getProduct(@PathVariable int id);
    

}
