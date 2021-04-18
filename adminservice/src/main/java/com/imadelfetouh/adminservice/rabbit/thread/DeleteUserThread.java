package com.imadelfetouh.adminservice.rabbit.thread;

import com.imadelfetouh.adminservice.rabbit.RabbitNonStopConsumer;
import com.imadelfetouh.adminservice.rabbit.consumer.DeleteUserConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteUserThread implements Runnable {

    private final static Logger logger = Logger.getLogger(DeleteUserThread.class.getName());

    @Override
    public void run() {
        while(true) {
            try {
                RabbitNonStopConsumer rabbitNonStopConsumer = new RabbitNonStopConsumer();
                DeleteUserConsumer deleteUserConsumer = new DeleteUserConsumer();

                rabbitNonStopConsumer.consume(deleteUserConsumer);
            } catch (Exception e) {
                logger.log(Level.ALL, e.getMessage());
            }
        }
    }
}
