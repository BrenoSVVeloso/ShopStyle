package com.history.history.service;

import java.util.Optional;

import com.history.history.entity.History;
import com.history.history.exception.ExceptionResponse;
import com.history.history.repository.HistoryRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.history.HistoryHDTO;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HistoryHDTO getHistory(int id) {
        try{    

            Optional<History> oHistory = this.historyRepository.findHistoryByUserId(id);

            System.out.println(oHistory.get());

            if(oHistory.isPresent()){
                return modelMapper.map(oHistory.get(), HistoryHDTO.class);
            }else{
                throw new ExceptionResponse(404, "History not found");
            }

        }catch(Exception e){
            throw new ExceptionResponse(400, e.getMessage());

        }

        
    }

    @Override
    public void saveHistory(History history) {
        historyRepository.save(history);
        
    }
    
}
