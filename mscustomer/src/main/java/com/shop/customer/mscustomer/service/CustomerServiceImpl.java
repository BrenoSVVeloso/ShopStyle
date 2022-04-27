package com.shop.customer.mscustomer.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.shop.customer.mscustomer.dto.LoginFormDTO;
import com.shop.customer.mscustomer.dto.TokenDTO;
import com.shop.customer.mscustomer.dto.UsuarioDTO;
import com.shop.customer.mscustomer.dto.UsuarioFormDTO;
import com.shop.customer.mscustomer.entity.Sex;
import com.shop.customer.mscustomer.entity.Usuario;
import com.shop.customer.mscustomer.exception.ExceptionResponse;
import com.shop.customer.mscustomer.repository.CustomerRepository;
import com.shop.customer.mscustomer.security.AutenticacaoService;
import com.shop.customer.mscustomer.security.TokenService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenDTO saveLogin(LoginFormDTO body) {

        UsernamePasswordAuthenticationToken dadosLogin = body.converter();
        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            Usuario usuario = (Usuario) autenticacaoService.loadUserByUsername(body.getEmail());
            return new TokenDTO(token,"Bearer");
        } catch (AuthenticationException e) {
            throw new ExceptionResponse(400, e.getMessage());
        }
    }

    @Override
    public UsuarioDTO saveUser(UsuarioFormDTO body) {
        
        try {
            
            Optional<Usuario> opUsuario = this.customerRepository.findByEmail(body.getEmail());

            if(!opUsuario.isPresent()){
                LocalDate date = LocalDate.parse(body.getBirthdate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Usuario usuario = modelMapper.map(body, Usuario.class);
                usuario.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
                usuario.setBirthdate(date);

                this.customerRepository.save(usuario);

                return modelMapper.map(usuario, UsuarioDTO.class);
            }else{
                throw new ExceptionResponse(404, "User Not Found");

            }
    
        } catch (Exception e) {
            throw new ExceptionResponse(400, e.getMessage());
        }
    }

    @Override
    public UsuarioDTO getUser(int id) {
        try{

            Optional<Usuario> opUsuario = this.customerRepository.findById(id);

            if(opUsuario.isPresent()){
                return modelMapper.map(opUsuario.get(), UsuarioDTO.class);
            }else{
        
            throw new ExceptionResponse(404, "User Not Found");
            }
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        } 
            
    }

    @Override
    public UsuarioDTO uptadeUser(int id, UsuarioFormDTO body) {
        Optional<Usuario> opUsuario;
        try{

             opUsuario = this.customerRepository.findById(id);

            if(opUsuario.isPresent()){
                opUsuario.get().setFirstName(body.getFirstName());
                opUsuario.get().setLastName(body.getLastName());

                body.setSex(body.getSex().toLowerCase());
                body.setSex(StringUtils.capitalize(body.getSex()));
                opUsuario.get().setSex(modelMapper.map(body.getSex(), Sex.class));
                
                opUsuario.get().setCpf(body.getCpf());
                LocalDate date = LocalDate.parse(body.getBirthdate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                opUsuario.get().setBirthdate(date);

                opUsuario.get().setEmail(body.getEmail());
                opUsuario.get().setPassword(body.getPassword());

                opUsuario.get().setActive(body.getActive());

                return modelMapper.map(opUsuario.get(), UsuarioDTO.class);

            }else{
                throw new ExceptionResponse(404, "User Not Found");

            }
 
        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());
        }    
    }

    @Override
    public UsuarioDTO getUserEmail(String id) {
        
        Optional<Usuario> oUsuario = this.customerRepository.findByEmail(id);
        if(oUsuario.isPresent()){
            return modelMapper.map(oUsuario.get(), UsuarioDTO.class);
        }else{
            throw new ExceptionResponse(404, "User not Found");
        }
        
    }
    
}
