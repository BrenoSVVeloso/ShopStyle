package com.bffshop.bffshop.dto.customer;

import java.time.LocalDate;

import com.bffshop.bffshop.entity.customer.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
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

    
    public boolean getActive(){
        return this.active;
    }
}   
