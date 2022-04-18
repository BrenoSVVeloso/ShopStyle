package com.history.history.entity;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import dto.history.PurchaseHDTO;
import dto.history.UserHDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class History implements Serializable, Comparable<History>{
    
    @Id
    private int id;

    @NotNull
    private UserHDTO user;

    @NotNull
    private PurchaseHDTO purchases;

    @Override
    public int compareTo(History h) {
        return this.id - h.getId();
    }
}
