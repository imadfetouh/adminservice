package com.imadelfetouh.adminservice.rabbit.thread;

import com.imadelfetouh.adminservice.rabbit.RabbitNonStopConsumer;
import com.imadelfetouh.adminservice.rabbit.consumer.ChangeRoleConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeRoleThread implements Runnable {

    private Logger logger = Logger.getLogger(ChangeRoleThread.class.getName());

    @Override
    public void run() {
        while(true) {
            try {
                RabbitNonStopConsumer rabbitNonStopConsumer = new RabbitNonStopConsumer();
                ChangeRoleConsumer changeRoleConsumer = new ChangeRoleConsumer();

                rabbitNonStopConsumer.consume(changeRoleConsumer);
            } catch (Exception e) {
                logger.log(Level.ALL, e.getMessage());
            }
        }
    }
}
