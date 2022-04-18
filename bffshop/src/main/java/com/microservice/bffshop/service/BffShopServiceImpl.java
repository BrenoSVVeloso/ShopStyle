package com.microservice.bffshop.service;

import java.util.List;

import javax.validation.Valid;

import com.microservice.bffshop.client.CatalogClient;
import com.microservice.bffshop.client.CheckoutClient;
import com.microservice.bffshop.client.CustomerClient;
import com.microservice.bffshop.client.HistoryClient;
import com.microservice.bffshop.dto.catalog.ProductDTO;
import com.microservice.bffshop.dto.checkout.PaymentDTO;
import com.microservice.bffshop.dto.checkout.PurchaseFormDTO;
import com.microservice.bffshop.dto.customer.UserDTO;
import com.microservice.bffshop.dto.customer.UserFormDTO;
import com.microservice.bffshop.dto.history.HistoryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BffShopServiceImpl implements BffShopService{

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private CatalogClient catalogClient;

    @Autowired
    private CheckoutClient checkoutClient;

    @Autowired
    private HistoryClient historyClient;

    @Override
    public UserDTO getUser(int id) {

        return this.customerClient.getUser(id);
    }

    @Override
    public void saveUser(@Valid  UserFormDTO body) {
        this.customerClient.saveUser(body);
        
    }

    @Override
    public UserDTO uptadeUser(int id, @Valid  UserFormDTO body) {
       
        return this.customerClient.updateUser(id, body);
    }

    @Override
    public ProductDTO getProduct(int id) {
        return this.catalogClient.getProduct(id);
    }

    @Override
    public List<PaymentDTO> getPaymets() {
        return this.checkoutClient.getPayments();
    }

    @Override
    public void savePurchases(@Valid PurchaseFormDTO body) {
        this.checkoutClient.savePurchases(body);
        
    }

    @Override
    public HistoryDTO getHistory(int id) {
        return this.historyClient.getHistory(id);
    }
    
    
}
