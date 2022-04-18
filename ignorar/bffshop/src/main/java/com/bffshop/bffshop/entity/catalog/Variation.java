package com.bffshop.bffshop.entity.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Variation {
    private int id;

    
    private String color;

   
    private Size size;

    
    private double price;
    
   
    private int quantity;

    
    private int product_id;

}
