package com.shop.customer.mscustomer.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.customer.mscustomer.entity.Sex;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    
    private Integer id;

    private String firstName;
    
    private String lastName;

    private Sex sex;
    
    private String cpf;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    
    private String email;

    private String password;
    
    private boolean active;
}
