package com.microservice.bffshop.dto.customer;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFormDTO {

    private String firstName;
    
    private String lastName;

    private String sex;
    
    private String cpf;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String birthdate;
    
    private String email;
    
    private String password;
    
    private boolean active;
}
