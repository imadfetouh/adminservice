package com.imadelfetouh.adminservice.rabbit.delivercallback;

import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.DeleteUserExecuter;
import com.imadelfetouh.adminservice.rabbit.RabbitProps;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteUserDeliverCallback implements DeliverCallback {

    private static final Logger logger = Logger.getLogger(DeleteUserDeliverCallback.class.getName());

    private RabbitProps rabbitProps;

    public DeleteUserDeliverCallback() {
        rabbitProps = RabbitProps.getInstance();
    }

    @Override
    public void handle(String s, Delivery delivery) throws IOException {
        try {
            if(!delivery.getProperties().getCorrelationId().equals(rabbitProps.getCorrId())) {
                String userId = new String(delivery.getBody(), StandardCharsets.UTF_8);

                Executer<Void> executer = new Executer<>(SessionType.WRITE);
                executer.execute(new DeleteUserExecuter(userId));
            }
        }
        catch (Exception e) {
            logger.log(Level.ALL, e.getMessage());
        }
    }
}
