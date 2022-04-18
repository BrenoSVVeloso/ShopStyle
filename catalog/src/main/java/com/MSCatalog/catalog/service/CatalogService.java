package com.MSCatalog.catalog.service;

import java.util.List;

import javax.validation.Valid;

import com.MSCatalog.catalog.dto.CategoryDTO;
import com.MSCatalog.catalog.dto.CategoryFormDTO;
import com.MSCatalog.catalog.dto.ProductDTO;
import com.MSCatalog.catalog.dto.ProductFormDTO;
import com.MSCatalog.catalog.dto.VariationDTO;
import com.MSCatalog.catalog.dto.VariationFormDTO;

public interface CatalogService {

            //------------------------------------------------------------- Products -------------------------------------------------------------------\\


    void saveProduct(@Valid ProductFormDTO body);

    List<ProductDTO> listAllProducts();

    ProductDTO getProduct(int id);

    ProductDTO uptadeProduct(@Valid ProductFormDTO body, Integer id);

    void deleteProduct(int id);

        //------------------------------------------------------------- Variations -------------------------------------------------------------------\\


    void saveVariation(@Valid VariationFormDTO body);

    VariationDTO uptadeVariation(@Valid VariationFormDTO body, Integer id);

    void deleteVariation(Integer id);

    //------------------------------------------------------------- Categories -------------------------------------------------------------------\\


    void saveCategory(@Valid CategoryFormDTO body);

    List<CategoryDTO> listAllCategories();

    CategoryDTO getCategory(Integer id);

    CategoryDTO uptadeCategory(Integer id, @Valid CategoryFormDTO body);

    void deleteCategory(Integer id);

    VariationDTO getVariation(int id);
    
}
