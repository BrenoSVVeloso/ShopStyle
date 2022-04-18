package com.MSCatalog.catalog.controller;

import java.util.List;

import javax.validation.Valid;

import com.MSCatalog.catalog.dto.CategoryDTO;
import com.MSCatalog.catalog.dto.CategoryFormDTO;
import com.MSCatalog.catalog.dto.ProductDTO;
import com.MSCatalog.catalog.dto.ProductFormDTO;
import com.MSCatalog.catalog.dto.VariationDTO;
import com.MSCatalog.catalog.dto.VariationFormDTO;
import com.MSCatalog.catalog.service.CatalogService;

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

@RestController
@RequestMapping("/v1")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;


    //------------------------------------------------------------- Products -------------------------------------------------------------------\\
    
    @PostMapping("/products")
    @Transactional
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductFormDTO body){
        catalogService.saveProduct(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> listAllProducts(){
        List<ProductDTO> produtoDTO = catalogService.listAllProducts();
        return ResponseEntity.ok(produtoDTO);

    }

    @GetMapping("/products/:{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id){
        ProductDTO productDTO = catalogService.getProduct(id);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/products/:{id}")
    @Transactional
    public ResponseEntity<ProductDTO> uptadeProduct(@Valid @RequestBody ProductFormDTO body, @PathVariable Integer id){
        ProductDTO productDTO = catalogService.uptadeProduct(body, id);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping("/products/:{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        catalogService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    //------------------------------------------------------------- Variations -------------------------------------------------------------------\\

    @PostMapping("/variations")
    @Transactional
    public ResponseEntity<VariationDTO> saveVariation(@Valid @RequestBody VariationFormDTO body){
        catalogService.saveVariation(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/variations/:{id}")
    public ResponseEntity<VariationDTO> getVariation(@PathVariable int id){
        VariationDTO variationDTO = catalogService.getVariation(id);
        return ResponseEntity.ok(variationDTO);
    }
    
    @PutMapping("/variations/:{id}")
    @Transactional
    public ResponseEntity<VariationDTO> uptadeVariation(@Valid @RequestBody VariationFormDTO body, @PathVariable Integer id){
        VariationDTO variationDTO = catalogService.uptadeVariation(body, id);
        return ResponseEntity.ok(variationDTO);
    }

    @DeleteMapping("/variations/:id")
    @Transactional
    public ResponseEntity<?> deleteVariation(@PathVariable Integer id){
       catalogService.deleteVariation(id);
       return ResponseEntity.ok().build(); 
    }


    //------------------------------------------------------------- Categories -------------------------------------------------------------------\\


    @PostMapping("/categories")
    @Transactional
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryFormDTO body){
        catalogService.saveCategory(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> listAllCategories(){
        List<CategoryDTO> categoryDTO = catalogService.listAllCategories();
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/categories/:{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id){
        CategoryDTO categoryDTO = catalogService.getCategory(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/categories/:{id}")
    @Transactional
    public ResponseEntity<CategoryDTO> uptadeCategory(@PathVariable Integer id, @Valid @RequestBody CategoryFormDTO body){
        CategoryDTO categoryDTO = catalogService.uptadeCategory(id, body);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/categories/:{id}")
    @Transactional
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        catalogService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    

}
