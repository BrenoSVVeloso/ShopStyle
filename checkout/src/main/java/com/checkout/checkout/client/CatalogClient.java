package com.checkout.checkout.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dto.catalog.ProductDTO;
import dto.catalog.VariationDTO;

@FeignClient("catalog")
public interface CatalogClient {
    
    @GetMapping("/v1/variations/:{id}")
    VariationDTO getVariation(@PathVariable int id);

    @GetMapping("/v1/products/:{id}")
    ProductDTO getProduct(@PathVariable int id);
}
