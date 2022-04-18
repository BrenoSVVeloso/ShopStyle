package com.MSCatalog.catalog.dto;

import com.MSCatalog.catalog.entity.Variation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private boolean active;

    private int[] category_ids;

    private Variation variation;
}
