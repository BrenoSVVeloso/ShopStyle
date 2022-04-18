package com.MSCatalog.catalog.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    
    private int id;

    @NotNull
    private String name;

    @NotNull
    private boolean active;
}
