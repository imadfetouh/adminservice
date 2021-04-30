package com.imadelfetouh.adminservice.rabbit.delivercallback;

import com.google.gson.Gson;
import com.imadelfetouh.adminservice.dal.configuration.Executer;
import com.imadelfetouh.adminservice.dal.configuration.SessionType;
import com.imadelfetouh.adminservice.dal.queryexecuter.AddUserExecuter;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.rabbit.RabbitProps;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserDeliverCallback implements DeliverCallback {

    private final static Logger logger = Logger.getLogger(AddUserDeliverCallback.class.getName());

    private Gson gson;
    private RabbitProps rabbitProps;

    public AddUserDeliverCallback() {
        gson = new Gson();
        rabbitProps = RabbitProps.getInstance();
    }

    @Override
    public void handle(String s, Delivery delivery) throws IOException {
        try {
            logger.info("Message received add user with corrId: " + delivery.getProperties().getCorrelationId());
            if(!delivery.getProperties().getCorrelationId().equals(rabbitProps.getCorrId())) {
                String json = new String(delivery.getBody(), StandardCharsets.UTF_8);
                NewUserDTO newUserDTO = gson.fromJson(json, NewUserDTO.class);

                Executer<Void> executer = new Executer<>(SessionType.WRITE);
                executer.execute(new AddUserExecuter(newUserDTO));
            }
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
}
