package com.shop.customer.mscustomer.service;

import com.shop.customer.mscustomer.dto.LoginFormDTO;
import com.shop.customer.mscustomer.dto.TokenDTO;
import com.shop.customer.mscustomer.dto.UsuarioDTO;
import com.shop.customer.mscustomer.dto.UsuarioFormDTO;

public interface CustomerService {

    TokenDTO saveLogin(LoginFormDTO body);

    UsuarioDTO saveUser(UsuarioFormDTO body);

    UsuarioDTO getUser(int id);

    UsuarioDTO uptadeUser(int id, UsuarioFormDTO body);

    UsuarioDTO getUserEmail(String id);
    

}
