package com.shop.customer.mscustomer.security;

import java.util.Optional;

import com.shop.customer.mscustomer.entity.Usuario;
import com.shop.customer.mscustomer.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> oUsuario = customerRepository.findByEmail(username);
		if(oUsuario.isPresent()){
			return oUsuario.get();
		}else{
			throw new UsernameNotFoundException("Dados inválidos");
		}
		
		
	}
	
	

}
