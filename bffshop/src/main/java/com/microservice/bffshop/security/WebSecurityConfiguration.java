package com.microservice.bffshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
