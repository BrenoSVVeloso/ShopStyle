package com.MSCatalog.catalog.consumer;

import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;

public class CustomErrorStrategy  extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    @Override
    public boolean isFatal(Throwable t){

        System.out.println( new String(((ListenerExecutionFailedException)t).getFailedMessage().getBody()));
        return t.getCause() instanceof IllegalArgumentException;
    }
    
}
