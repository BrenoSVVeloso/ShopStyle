package com.microservice.bffshop.service;

import java.util.List;

import javax.validation.Valid;

import com.microservice.bffshop.dto.TokenDTO;
import com.microservice.bffshop.dto.catalog.ProductDTO;
import com.microservice.bffshop.dto.checkout.PaymentDTO;
import com.microservice.bffshop.dto.checkout.PurchaseFormDTO;
import com.microservice.bffshop.dto.customer.LoginFormDTO;
import com.microservice.bffshop.dto.customer.UserDTO;
import com.microservice.bffshop.dto.customer.UserFormDTO;
import com.microservice.bffshop.dto.history.HistoryDTO;

public interface BffShopService {

    UserDTO getUser(int id);

    void saveUser(@Valid UserFormDTO body);

    UserDTO uptadeUser(int id, @Valid UserFormDTO body);

    ProductDTO getProduct(int id);

    List<PaymentDTO> getPaymets();

    void savePurchases(@Valid PurchaseFormDTO body);

    HistoryDTO getHistory(int id);

    TokenDTO saveLogin(@Valid LoginFormDTO body);
    
}
