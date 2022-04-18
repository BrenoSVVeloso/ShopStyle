package com.MSCatalog.catalog.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Comparable<Product>{
    
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private boolean active;

    @NotNull
    private int[] category_ids;

    @DBRef
    private Variation variation;

    @Override
    public int compareTo(Product p) {
        return this.id - p.getId();
    }

}
