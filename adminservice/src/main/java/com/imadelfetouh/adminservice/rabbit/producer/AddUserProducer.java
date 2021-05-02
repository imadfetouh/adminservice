package com.imadelfetouh.adminservice.rabbit.producer;

import com.google.gson.Gson;
import com.imadelfetouh.adminservice.model.dto.NewUserDTO;
import com.imadelfetouh.adminservice.rabbit.Producer;
import com.imadelfetouh.adminservice.rabbit.RabbitProps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddUserProducer implements Producer {

    private static final Logger logger = Logger.getLogger(AddUserProducer.class.getName());

    private final NewUserDTO newUserDTO;
    private final String exchange_name;
    private final Gson gson;

    public AddUserProducer(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
        this.exchange_name = "adduserexchange";
        gson = new Gson();
    }

    @Override
    public void produce(Channel channel) {
        try {
            channel.exchangeDeclare(exchange_name, "direct", true);
            String json = gson.toJson(newUserDTO);

            AMQP.BasicProperties properties = RabbitProps.getInstance().createProperties();

            channel.basicPublish(exchange_name, "", properties, json.getBytes());

            logger.info("Message add user send");
        }
        catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
}
