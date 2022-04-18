package com.MSCatalog.catalog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.MSCatalog.catalog.dto.CategoryDTO;
import com.MSCatalog.catalog.dto.CategoryFormDTO;
import com.MSCatalog.catalog.dto.ProductDTO;
import com.MSCatalog.catalog.dto.ProductFormDTO;
import com.MSCatalog.catalog.dto.VariationDTO;
import com.MSCatalog.catalog.dto.VariationFormDTO;
import com.MSCatalog.catalog.entity.Category;
import com.MSCatalog.catalog.entity.Product;
import com.MSCatalog.catalog.entity.Variation;
import com.MSCatalog.catalog.exception.ExceptionResponse;
import com.MSCatalog.catalog.repository.CategoryRepository;
import com.MSCatalog.catalog.repository.ProductRepository;
import com.MSCatalog.catalog.repository.VariationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariationRepository variationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DiscoveryClient eurekaClient;
        //------------------------------------------------------------- Products -------------------------------------------------------------------\\

    @Override
    public void saveProduct(@Valid ProductFormDTO body) {
        try{    
            Product product = this.modelMapper.map(body, Product.class);
            List<Product> allProduct = productRepository.findAll();
            boolean active = true;
            if((allProduct.size()-1 >=0)){

                Collections.sort(allProduct, (p1,p2) ->{
                    return p2.getId() - p1.getId();
                });
                Product productMaxId = allProduct.get(0);
                product.setId(productMaxId.getId()+1);
            }else{
                product.setId(1);
            }
            int[] ids = product.getCategory_ids();
            for(int i = 0;i<ids.length;i++){

                if(!categoryRepository.findById(ids[i]).get().getActive()){
                    active = false;
                }
            }
            if(active){    

                Optional<Variation> variation = variationRepository.findBy_Product_id(product.getId());
                if(!variation.isPresent()){ 
                    productRepository.save(product);
                    System.out.println(product);

                }else{
                    product.setVariation(variation.get());
                    productRepository.save(product);
                    System.out.println(product);                
                }
            }else{
                    throw new ExceptionResponse(400, "Produto não está ativo");
            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }
    }

    @Override
    public List<ProductDTO> listAllProducts() {
        try{    
            List<Product> products = productRepository.findAll();
            if(products.isEmpty()){
                throw new ExceptionResponse(404, "Lista vazia");
            }else{
                List<ProductDTO> productDTOs = new ArrayList<>();
                products.forEach((pr) -> {
                    productDTOs.add(modelMapper.map(pr, ProductDTO.class));
                });
            
                return productDTOs;
            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }

    }

    @Override
    public ProductDTO getProduct(int id) {
        try{
            Optional<Product> oProduct = this.productRepository.findById(id);
            if(oProduct.isPresent()){
                return this.modelMapper.map(productRepository.findById(id), ProductDTO.class);

            }else{

                throw new ExceptionResponse(404, "Product not found");
            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }
    }

    @Override
    public ProductDTO uptadeProduct(@Valid ProductFormDTO body, Integer id) {
        try{    
            Optional<Product> oProduct = this.productRepository.findById(id);
            if(oProduct.isPresent()){
                
                oProduct.get().setName(body.getName());
                oProduct.get().setDescription(body.getDescription());
                oProduct.get().setActive(body.getActive());
                oProduct.get().setCategory_ids(body.getCategory_ids());
                this.productRepository.save(oProduct.get());
                return modelMapper.map(oProduct.get(), ProductDTO.class);
            }else{
                throw new ExceptionResponse(404, "Product Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }
        
    }

    @Override
    public void deleteProduct(int id) {
        try{    
            Optional<Product> oProduct = this.productRepository.findById(id);
            if(oProduct.isPresent()){
                this.productRepository.deleteById(id);
            }else{
                throw new ExceptionResponse(404, "Product Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }
    }

        //------------------------------------------------------------- Variations -------------------------------------------------------------------\\


    @Override
    public void saveVariation(@Valid VariationFormDTO body) {     
        try{
            Variation variation = this.modelMapper.map(body, Variation.class);
            List<Variation> allVariations = variationRepository.findAll();

            if((allVariations.size()-1 >=0)){
                Variation variationMaxId = allVariations.get(allVariations.size()-1);
                variation.setId(variationMaxId.getId()+1);
            }else{
                variation.setId(1);
            }
            
            int productId = body.getProduct_id();
            Optional<Product> oProduct = this.productRepository.findById(productId);
            if(oProduct.isPresent()){
                oProduct.get().setVariation(variation);
                this.productRepository.save(oProduct.get());
            }else{
                throw new ExceptionResponse(404, "Product Not Found");

            }
            this.variationRepository.save(variation);
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }
    }

    @Override
    public VariationDTO getVariation(int id) {
        try{    
            Optional<Variation> oVariation = variationRepository.findById(id);
            if(oVariation.isPresent()){
                return modelMapper.map(oVariation.get(), VariationDTO.class);
            }else{
                throw new ExceptionResponse(404, "Variation Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public VariationDTO uptadeVariation(@Valid VariationFormDTO body, Integer id) {
        try{
            Optional<Variation> oVariation = this.variationRepository.findById(id);

            if(oVariation.isPresent()){    
                oVariation.get().setColor(body.getColor());
                oVariation.get().setQuantity(body.getQuantity());
                oVariation.get().setPrice(body.getPrice());
                oVariation.get().setSize(body.getSize());
                
                int productId = body.getProduct_id();
                Optional<Product> oProduct = this.productRepository.findById(productId);
                if(oProduct.isPresent()){
                    oVariation.get().setProduct_id(body.getProduct_id());
                    oProduct.get().setVariation(oVariation.get());
                    this.productRepository.save(oProduct.get());
                } 
            
            this.variationRepository.save(oVariation.get());
            }else{
                throw new ExceptionResponse(404,"Variation Not Found");

            }

            return modelMapper.map(oVariation.get(), VariationDTO.class);
        }catch(Exception e ){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public void deleteVariation(Integer id) {
        try{    
            Optional<Variation> oVariation = this.variationRepository.findById(id);
            if(oVariation.isPresent()){
                this.variationRepository.deleteById(id);
            }else{
                throw new ExceptionResponse(404, "Variation Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    //------------------------------------------------------------- Categories -------------------------------------------------------------------\\

    @Override
    public void saveCategory(@Valid CategoryFormDTO body) {
        try{
            Category category = this.modelMapper.map(body, Category.class);
            List<Category> allCategory = categoryRepository.findAll();

            if((allCategory.size()-1 >=0)){
                Category categoryMaxId = allCategory.get(allCategory.size()-1);
                category.setId(categoryMaxId.getId()+1);
            }else{
                category.setId(1);
            }
            this.categoryRepository.save(category);
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }
    
    @Override
    public List<CategoryDTO> listAllCategories() {
        try{
            List<Category> categories = categoryRepository.findAll();
            List<CategoryDTO> categoryDTOs = new ArrayList<>();
            categories.forEach((ca) -> {
                categoryDTOs.add(modelMapper.map(ca, CategoryDTO.class));
            });

            return categoryDTOs;
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public CategoryDTO getCategory(Integer id) {
        try{
            Optional<Category> oCategory = this.categoryRepository.findById(id);
            if(oCategory.isPresent()){
                return this.modelMapper.map(categoryRepository.findById(id), CategoryDTO.class);
            }else{
                
                throw new ExceptionResponse(404, "Category Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
        
    }

    @Override
    public CategoryDTO uptadeCategory(Integer id, @Valid CategoryFormDTO body) {
        try{    
            Optional<Category> oCategory = this.categoryRepository.findById(id);

            if(oCategory.isPresent()){    
                oCategory.get().setName(body.getName());
                oCategory.get().setActive(body.getActive());
                this.categoryRepository.save(oCategory.get());
                return modelMapper.map(oCategory.get(), CategoryDTO.class);

            }else{
                throw new ExceptionResponse(404, "Category Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try{
            Optional<Category> oCategory = this.categoryRepository.findById(id);
            if(oCategory.isPresent()){
                this.categoryRepository.deleteById(id);
            }else{
                throw new ExceptionResponse(404, "Category Not Found");

            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }
    }

    

        

        
    
}
