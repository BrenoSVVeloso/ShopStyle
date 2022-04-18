package com.history.history.service;

import com.history.history.entity.History;

import dto.history.HistoryHDTO;

public interface HistoryService {

    HistoryHDTO getHistory(int id);

    void saveHistory(History history);
    
}
