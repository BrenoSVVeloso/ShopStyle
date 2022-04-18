package com.microservice.bffshop.security;

import java.util.Optional;

import com.microservice.bffshop.client.CustomerClient;
import com.microservice.bffshop.dto.customer.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	private CustomerClient customerClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO usuario = customerClient.findByEmail(username);
		return (UserDetails) usuario;
	}
	
	

}
