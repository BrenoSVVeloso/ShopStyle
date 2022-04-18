package com.microservice.bffshop.dto.history;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice.bffshop.dto.checkout.PaymentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistoryDTO {

    private int id;

    private PaymentDTO paymentMethod;


    private ProductHistoryDTO[] product;

    private double total;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;
}
