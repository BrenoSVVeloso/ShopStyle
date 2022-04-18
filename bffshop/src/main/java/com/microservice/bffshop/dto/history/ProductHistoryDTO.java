package com.microservice.bffshop.dto.history;

import javax.validation.constraints.NotNull;

import com.microservice.bffshop.dto.catalog.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHistoryDTO {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String color;

    @NotNull
    private Size size;

    @NotNull
    private double price;

    @NotNull
    private int quantity;
}
