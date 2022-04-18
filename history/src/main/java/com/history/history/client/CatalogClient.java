// package com.history.history.client;


// import com.history.history.dto.catalog.ProductFormDTO;
// import com.history.history.dto.catalog.VariationDTO;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient("catalog")
// public interface CatalogClient {

//     @GetMapping("/v1/variations/:{id}")
//     VariationDTO getVariation(@PathVariable int id);

//     @GetMapping("/v1/products/:{id}")
//     ProductFormDTO getProduct(@PathVariable int id);
    
// }
