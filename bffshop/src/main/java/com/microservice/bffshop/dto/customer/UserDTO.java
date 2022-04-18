package com.microservice.bffshop.dto.customer;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails{
    
    private Integer id;

    private String firstName;
    
    private String lastName;

    private Sex sex;
    
    private String cpf;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    
    private String email;

    private String password;
    
    private boolean active;

    
    public boolean getActive(){
        return this.active;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }
}   
