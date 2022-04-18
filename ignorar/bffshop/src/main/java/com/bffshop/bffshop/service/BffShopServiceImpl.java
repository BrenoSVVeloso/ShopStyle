package com.bffshop.bffshop.service;

import com.bffshop.bffshop.client.CustomerClient;
import com.bffshop.bffshop.dto.customer.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BffShopServiceImpl implements BffShopService{

    @Autowired
    private CustomerClient customerClient;

    @Override
    public UserDTO getUser(int id) {

        return customerClient.getUser(id);
    }
    
    
}
