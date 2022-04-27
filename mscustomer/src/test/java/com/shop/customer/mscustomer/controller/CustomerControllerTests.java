package com.shop.customer.mscustomer.controller;

import com.shop.customer.mscustomer.exception.ExceptionResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CustomerControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaDevolver400CasoAlgumDadoEstejaIncorretoNoPost() throws Exception{
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTaG9wIFN0eWxlIiwic3ViIjoiNyIsImlhdCI6MTY1MTA4NjM0NSwiZXhwIjoxNjUxMDg5OTQ1fQ.cEHQB5qKsXp7AQueuHHwRe54-olVbJEdiBQ-fy78A38";
        String json = "{\"firstName\": \"Saad\",\"lastName\": \"Hack\",\"sex\": \"masculino\",\"cpf\": \"127.027.666-25\",\"birthdate\": \"22/04/1993\",\"email\": \"saadhack@email.com\",\"password\": \"12345678\",\"active\": true}";
        RequestBuilder request = MockMvcRequestBuilders
        .post("/v1/users")
        .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
        .content(json)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
