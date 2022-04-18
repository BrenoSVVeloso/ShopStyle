package com.microservice.bffshop.dto.history;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice.bffshop.dto.customer.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Sex sex;
    
    @NotNull
    private String cpf;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    
    @NotNull
    private String email;
}
