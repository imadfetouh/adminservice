package com.imadelfetouh.adminservice.rabbit.thread;

import com.imadelfetouh.adminservice.rabbit.RabbitNonStopConsumer;
import com.imadelfetouh.adminservice.rabbit.consumer.DefaultConsumer;
import com.imadelfetouh.adminservice.rabbit.delivercallback.ChangeRoleDeliverCallback;
import com.rabbitmq.client.DeliverCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeRoleThread implements Runnable {

    private Logger logger = Logger.getLogger(ChangeRoleThread.class.getName());

    private final String queue_name;
    private final String exchange_name;
    private final DeliverCallback deliverCallback;

    public ChangeRoleThread() {
        queue_name = "adminservice_changeroleconsumer";
        exchange_name = "changeroleexchange";
        deliverCallback = new ChangeRoleDeliverCallback();
    }

    @Override
    public void run() {
        int count = 1;
        while(true) {
            try {
                count++;
                RabbitNonStopConsumer rabbitNonStopConsumer = new RabbitNonStopConsumer();
                DefaultConsumer defaultConsumer = new DefaultConsumer(queue_name, exchange_name, deliverCallback);

                rabbitNonStopConsumer.consume(defaultConsumer);
            } catch (Exception e) {
                logger.severe(e.getMessage());
                if(count == 1) {
                    break;
                }
            }
        }
    }
}
