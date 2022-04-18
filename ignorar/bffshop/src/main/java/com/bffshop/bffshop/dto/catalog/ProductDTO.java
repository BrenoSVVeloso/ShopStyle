package com.bffshop.bffshop.dto.catalog;

import com.bffshop.bffshop.entity.catalog.Variation;

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

    private Variation variation;
}
