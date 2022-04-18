package com.shop.customer.mscustomer.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFormDTO {

    private String firstName;
    
    private String lastName;

    private String sex;
    
    private String cpf;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String birthdate;
    
    private String email;
    
    private String password;
    
    private boolean active;

    public boolean getActive(){
        return this.active;
    }
}
