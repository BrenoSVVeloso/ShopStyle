package com.microservice.bffshop.client;

import com.microservice.bffshop.dto.history.HistoryDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("history")
public interface HistoryClient {

    @GetMapping("/v1/historic/user/:{id}")
    HistoryDTO getHistory(@PathVariable int id);

    
}
