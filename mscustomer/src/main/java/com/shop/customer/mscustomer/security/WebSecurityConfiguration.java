package com.shop.customer.mscustomer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/v1/users").permitAll()
        .antMatchers(HttpMethod.POST, "/v1/login").permitAll()
        .antMatchers(HttpMethod.GET,"/v1/users/:{id}").permitAll()
        .antMatchers(HttpMethod.PUT,"/v1/users/:{id}").permitAll()
        .antMatchers(HttpMethod.GET,"/v1/products/:{id}").permitAll()
        .antMatchers(HttpMethod.GET,"/v1/categories/:{id}/products").permitAll()
        .antMatchers(HttpMethod.GET,"/v1/payments").permitAll()
        .antMatchers(HttpMethod.POST,"/v1/purchases").permitAll()
        .antMatchers(HttpMethod.GET,"/v1/historic/user/:{id}").permitAll()

        .anyRequest().authenticated();
    }
}
