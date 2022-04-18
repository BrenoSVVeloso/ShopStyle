package com.MSCatalog.catalog.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import constants.RabbitMqConstants;
import dto.EstoqueDTO;

@Component
public class EstoqueConsumer {
    
    @RabbitListener(queues = RabbitMqConstants.FILA_ESTOQUE)
    private void consumer(EstoqueDTO estoqueDTO){
        System.out.println("Id produto: " + estoqueDTO.getPurchaseId() + " \nQuantidade: " + estoqueDTO.getQuantity());

    }
}