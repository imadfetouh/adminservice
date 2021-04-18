package com.imadelfetouh.adminservice.rabbit.producer;

import com.imadelfetouh.adminservice.rabbit.Producer;
import com.imadelfetouh.adminservice.rabbit.RabbitProps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteUserProducer implements Producer {

    private static final Logger logger = Logger.getLogger(DeleteUserProducer.class.getName());

    private final String userId;
    private final String exchange_name;

    public DeleteUserProducer(String userId) {
        this.userId = userId;
        this.exchange_name = "deleteuserexchange";
    }

    @Override
    public void produce(Channel channel) {
        try {
            channel.exchangeDeclare(exchange_name, "direct", true);

            AMQP.BasicProperties properties = RabbitProps.getInstance().createProperties();

            channel.basicPublish(exchange_name, "", properties, userId.getBytes());
        }
        catch (Exception e) {
            logger.log(Level.ALL, e.getMessage());
        }
    }
}
