package com.imadelfetouh.adminservice.rabbit.thread;

import com.imadelfetouh.adminservice.rabbit.RabbitNonStopConsumer;
import com.imadelfetouh.adminservice.rabbit.consumer.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;

import java.util.logging.Logger;

public class StartConsuming {

    private static final Logger logger = Logger.getLogger(StartConsuming.class.getName());

    private String queue_name;
    private String exchange_name;
    private DeliverCallback deliverCallback;

    public StartConsuming(String queue_name, String exchange_name, DeliverCallback deliverCallback) {
        this.queue_name = queue_name;
        this.exchange_name = exchange_name;
        this.deliverCallback = deliverCallback;
    }

    public void start() {
        int count = 1;
        while(true) {
            try {
                count++;
                RabbitNonStopConsumer rabbitNonStopConsumer = new RabbitNonStopConsumer();
                DefaultConsumer defaultConsumer = new DefaultConsumer(queue_name, exchange_name, deliverCallback);

                rabbitNonStopConsumer.consume(defaultConsumer);
                if(count == 0) {
                    break;
                }
            } catch (Exception e) {
                logger.severe(e.getMessage());
                if(count == 0){
                    break;
                }
            }
        }
    }
}
