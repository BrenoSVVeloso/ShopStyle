package com.checkout.checkout.service;

import java.util.List;

import javax.validation.Valid;

import dto.PaymentDTO;
import dto.PaymentFormDTO;
import dto.PurchaseDTO;
import dto.PurchaseFormDTO;

public interface CheckoutService {

    void savePayment(@Valid PaymentFormDTO body);

    List<PaymentDTO> listPayments();

    PaymentDTO getPayment(int id);

    PaymentDTO uptadePayment(@Valid PaymentFormDTO body, int id);

    void deletePayment(int id);

    PurchaseDTO purchase(@Valid PurchaseFormDTO body);

    PurchaseDTO getPurchaseByUserId(int id);
    
}
