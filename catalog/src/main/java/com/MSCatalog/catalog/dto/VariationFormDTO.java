package com.MSCatalog.catalog.dto;

import javax.validation.constraints.NotNull;

import com.MSCatalog.catalog.entity.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationFormDTO {

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
