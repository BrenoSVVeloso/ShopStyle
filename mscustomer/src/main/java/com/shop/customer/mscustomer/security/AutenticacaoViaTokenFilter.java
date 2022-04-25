package com.shop.customer.mscustomer.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.customer.mscustomer.entity.Usuario;
import com.shop.customer.mscustomer.repository.CustomerRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;

	private CustomerRepository customerRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, CustomerRepository customerRepository) {
		this.tokenService = tokenService;
		this.customerRepository = customerRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if(valido) {
			autenticarUsuario(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		Integer idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> user = this.customerRepository.findById(idUsuario);
		if(user.isPresent()){
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
