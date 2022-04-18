package com.MSCatalog.catalog.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFormDTO {
    
    @NotNull
    private String name;

    @NotNull
    private boolean active;


    public boolean getActive(){
        return this.active;
    }
}
