package com.imadelfetouh.adminservice.rabbit.consumer;

import com.imadelfetouh.adminservice.rabbit.Monitor;
import com.imadelfetouh.adminservice.rabbit.NonStopConsumer;
import com.imadelfetouh.adminservice.rabbit.delivercallback.AddUserDeliverCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserConsumer implements NonStopConsumer {

    private static final Logger logger = Logger.getLogger(AddUserConsumer.class.getName());

    private final String queue_name;
    private final String exchange_name;

    public AddUserConsumer() {
        queue_name = "adminservice_adduserconsumer";
        exchange_name = "adduserexchange";
    }

    @Override
    public void consume(Channel channel) {
        try {
            channel.queueDeclare(queue_name, false, false, false, null);
            channel.exchangeDeclare(exchange_name, "direct", true);
            channel.queueBind(queue_name, exchange_name, "");

            DeliverCallback deliverCallback = new AddUserDeliverCallback();

            channel.basicConsume(queue_name, true, deliverCallback, s -> {});

            Monitor monitor = new Monitor();
            monitor.start();
        }
        catch (IOException e) {
            logger.log(Level.ALL, e.getMessage());
        }
    }
}
