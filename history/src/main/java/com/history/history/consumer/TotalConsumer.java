package com.history.history.consumer;

import java.util.Collections;
import java.util.List;

import com.history.history.entity.History;
import com.history.history.repository.HistoryRepository;
import com.history.history.service.HistoryService;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import constants.RabbitMqConstants;
import dto.history.HistoryHDTO;

@Component
public class TotalConsumer {
    
    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ModelMapper modelMapper;

    @RabbitListener(queues = RabbitMqConstants.FILA_PRECO)
    @Transactional
    private void consumer(HistoryHDTO historyHDTO){
        System.out.println("\n\n" + historyHDTO + "\n\n");
        History history = this.modelMapper.map(historyHDTO, History.class);

        if(this.historyRepository.count() <= 0){
            history.setId(1);
        }else{
            List<History> allHistory = (List<History>) this.historyRepository.findAll();
            Collections.sort(allHistory, (h1,h2) ->{
                return h2.getId() - h1.getId();
            });
            history.setId(allHistory.get(0).getId());
        }

        historyService.saveHistory(history);

    }
}
