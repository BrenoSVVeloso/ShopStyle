package com.microservice.bffshop.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private boolean active;

    private int[] category_ids;

    private VariationDTO variation;
}
