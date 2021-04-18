package com.imadelfetouh.adminservice.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitConfiguration {

    private static final Logger logger = Logger.getLogger(RabbitConfiguration.class.getName());

    private static final RabbitConfiguration rabbitConfiguration = new RabbitConfiguration();
    private final ConnectionFactory connectionFactory;
    private Connection connection;

    private RabbitConfiguration() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(System.getenv("RABBIT_HOST"));
        connection = createConnection();
    }

    public static RabbitConfiguration getInstance() {
        return rabbitConfiguration;
    }

    private Connection createConnection() {
        int count = 0;
        int maxCount = 3;

        while(true) {
            try {
                count++;
                connection = connectionFactory.newConnection();
                return connection;
            } catch (IOException | TimeoutException e) {
                logger.log(Level.ALL, e.getMessage());
                if(count == maxCount){
                    return null;
                }
            }
        }
    }

    public Channel getChannel() {
        try {
            return connection.createChannel();
        } catch (IOException e) {
            logger.log(Level.ALL, e.getMessage());
            return null;
        }
    }
}
