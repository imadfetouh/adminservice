package com.imadelfetouh.adminservice.rabbit.producer;

import com.google.gson.Gson;
import com.imadelfetouh.adminservice.model.dto.ChangeRoleDTO;
import com.imadelfetouh.adminservice.rabbit.Producer;
import com.imadelfetouh.adminservice.rabbit.RabbitProps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeRoleProducer implements Producer {

    private static final Logger logger = Logger.getLogger(ChangeRoleProducer.class.getName());

    private final ChangeRoleDTO changeRoleDTO;
    private final String exchange_name;
    private final Gson gson;

    public ChangeRoleProducer(ChangeRoleDTO changeRoleDTO) {
        this.changeRoleDTO = changeRoleDTO;
        this.exchange_name = "changeroleexchange";
        this.gson = new Gson();
    }

    @Override
    public void produce(Channel channel) {
        try {
            channel.exchangeDeclare(exchange_name, "direct", true);
            String json = gson.toJson(changeRoleDTO);

            AMQP.BasicProperties properties = RabbitProps.getInstance().createProperties();

            channel.basicPublish(exchange_name, "", properties, json.getBytes());
        }
        catch (Exception e) {
            logger.log(Level.ALL, e.getMessage());
        }
    }
}
