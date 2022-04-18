package com.MSCatalog.catalog.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variation{
    
    @Id
    // @GeneratedValue(strategy=  GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String color;

    @NotNull
    private Size size;

    @NotNull
    private double price;
    
    @NotNull
    private int quantity;

    @NotNull
    private int product_id;

}
