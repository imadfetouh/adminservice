package com.imadelfetouh.adminservice.rabbit.thread;

import com.imadelfetouh.adminservice.rabbit.RabbitNonStopConsumer;
import com.imadelfetouh.adminservice.rabbit.consumer.AddUserConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserThread implements Runnable {

    private Logger logger = Logger.getLogger(AddUserConsumer.class.getName());

    @Override
    public void run() {
        while(true) {
            try {
                RabbitNonStopConsumer rabbitNonStopConsumer = new RabbitNonStopConsumer();
                AddUserConsumer addUserConsumer = new AddUserConsumer();

                rabbitNonStopConsumer.consume(addUserConsumer);
            } catch (Exception e) {
                logger.log(Level.ALL, e.getMessage());
            }
        }
    }
}
