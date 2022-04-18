package com.history.history.controller;

import com.history.history.service.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.history.HistoryHDTO;

@RestController
@RequestMapping("/v1")
public class HistoryController {
    
    @Autowired
    private HistoryService historyService;

    @GetMapping("/historic/user/:{id}")
    public ResponseEntity<HistoryHDTO> getHistory(@PathVariable int id){
        HistoryHDTO historyDTOs = this.historyService.getHistory(id);
        return ResponseEntity.ok(historyDTOs);

    }
}
