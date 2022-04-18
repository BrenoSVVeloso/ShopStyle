package com.MSCatalog.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductFormDTO {

    private String name;

    private String description;

    private boolean active;

    private int[] category_ids;

    public boolean getActive(){
        return this.active;
    }

}
